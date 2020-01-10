package pl.edu.wszib.services;

import pl.edu.wszib.model.User;

public interface IUserService {
    void registerUser(User user);
    User getUserByLogin(String login);
}
