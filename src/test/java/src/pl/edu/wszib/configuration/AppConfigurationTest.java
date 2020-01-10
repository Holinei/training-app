package src.pl.edu.wszib.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.controllers.AuthorizationController;
import pl.edu.wszib.dao.IUserDAO;
import pl.edu.wszib.dao.impl.UserDAOImpl;
import pl.edu.wszib.services.IAuthenticationService;
import pl.edu.wszib.services.IUserService;
import pl.edu.wszib.services.impl.AuthenticationServiceImpl;
import pl.edu.wszib.services.impl.UserServiceImpl;
import src.pl.edu.wszib.dao.impl.UserDAOImplStub;

@Configuration
public class AppConfigurationTest {

//    @Bean
//    public IUserDAO userDAO() {
//        return new UserDAOImplStub();
//    }

    @Bean
    public IAuthenticationService authenticationService(IUserDAO userDAO) {
        return new AuthenticationServiceImpl(userDAO);
    }

    @Bean
    public IUserService userService(IUserDAO userDAO) {
        return new UserServiceImpl(userDAO);
    }

    @Bean
    public AuthorizationController authorizationController(){
        return new AuthorizationController();
    }
}
