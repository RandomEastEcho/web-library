package library.dao;

import java.util.Optional;
import library.model.Role;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> getByName(Role.RoleName roleName);
}
