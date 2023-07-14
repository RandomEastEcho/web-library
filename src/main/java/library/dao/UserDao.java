package library.dao;

import java.util.List;
import java.util.Optional;
import library.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    User update(User user);

    Optional<User> findByEmail(String email);
}
