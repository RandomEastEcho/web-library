package library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder,
                          AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/librarian").hasRole("LIBRARIAN")
                .antMatchers("/home/reader").hasRole("READER")
                .antMatchers("/registration-form").permitAll()
                .antMatchers("/books/actions", "/books/add", "/books/search-by-id")
                .hasRole("LIBRARIAN")
                .antMatchers("/books/search-by-id-reader").hasRole("READER")
                .antMatchers(HttpMethod.GET, "/books",
                        "/books/{id}").hasAnyRole("LIBRARIAN", "READER")
                .antMatchers(HttpMethod.GET, "/categories", "/categories/{id}", "/users",
                        "/users/{id}", "/categories/actions", "/categories/add",
                        "/categories/search-by-id","/categories/all-categories",
                        "/users/actions", "/users/search-by-id", "/users/all-users")
                .hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/books", "/categories").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.PUT, "/books/{id}", "/categories/{id}", "/users/{id}")
                .hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/books/{id}", "/categories/{id}")
                .hasRole("LIBRARIAN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}
