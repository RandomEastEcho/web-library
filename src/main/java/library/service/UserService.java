package library.service;

import java.util.List;
import java.util.Optional;
import library.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User update(User user);

    List<User> getAll();

    Optional<User> findByEmail(String email);
}
