package library.service;

import library.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
