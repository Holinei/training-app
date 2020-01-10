package pl.edu.wszib.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.dao.IUserDAO;
import pl.edu.wszib.model.User;
import pl.edu.wszib.services.IUserService;

public class UserServiceImpl implements IUserService {

    IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void registerUser(User user) {
        User userFromDb = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDb == null) {
            user.setPass(DigestUtils.md5Hex(user.getPass()));
            this.userDAO.saveUser(user);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }
}
