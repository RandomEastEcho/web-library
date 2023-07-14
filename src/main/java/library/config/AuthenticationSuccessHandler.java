package library.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler
        implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        boolean isLibrarian = authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_LIBRARIAN"));
        boolean isReader = authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_READER"));

        if (isLibrarian) {
            System.out.println("Librarian authentication successful");
            response.sendRedirect("/home/librarian");
        } else if (isReader) {
            System.out.println("Reader authentication successful");
            response.sendRedirect("/home/reader");
        }
    }
}
