package pl.edu.wszib.dao.impl;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.dao.IUserDAO;
import pl.edu.wszib.model.User;

import java.util.List;

public class UserDAOImpl implements IUserDAO {

    SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(User user) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        User user = (User) session
                .createQuery("FROM pl.edu.wszib.model.User WHERE login = '" + login + "'")
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        List<User> users = session
                .createQuery("FROM pl.edu.wszib.model.User")
                .list();
        session.close();
        return users;
    }
}
