package library.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import library.model.Role;
import library.model.User;
import library.service.RoleService;
import library.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role librarianRole = new Role();
        librarianRole.setRoleName(Role.RoleName.LIBRARIAN);
        roleService.add(librarianRole);

        Role readerRole = new Role();
        readerRole.setRoleName(Role.RoleName.READER);
        roleService.add(readerRole);

        User librarian = new User();
        librarian.setEmail("librarian@i.ua");
        librarian.setPassword("wh40k");
        librarian.setRoles(Set.of(librarianRole));
        userService.add(librarian);

        User reader = new User();
        reader.setEmail("reader@i.ua");
        reader.setPassword("red40");
        reader.setRoles(Set.of(readerRole));
        userService.add(reader);
    }
}
