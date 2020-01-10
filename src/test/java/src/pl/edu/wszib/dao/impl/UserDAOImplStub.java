package src.pl.edu.wszib.dao.impl;

import pl.edu.wszib.dao.IUserDAO;
import pl.edu.wszib.model.User;

import java.util.List;
// udaje bd
public class UserDAOImplStub implements IUserDAO {
    @Override
    public void saveUser(User user) {

    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        user.setId(1);
        user.setLogin("mateusz");
        user.setPass("617f41f48d1d4f9c787aed6b0ebc6f7d");
        user.setAge(23);
        user.setName("mateusz");
        user.setSurname("mateusz");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
