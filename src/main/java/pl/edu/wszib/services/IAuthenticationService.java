package pl.edu.wszib.services;

import pl.edu.wszib.model.User;

public interface IAuthenticationService {
    boolean authenticateUser(User user);
}
