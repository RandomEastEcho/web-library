package library.service;

import library.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getByName(String roleName);
}
