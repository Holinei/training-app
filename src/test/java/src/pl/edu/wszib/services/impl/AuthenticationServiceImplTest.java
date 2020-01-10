package src.pl.edu.wszib.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.wszib.dao.IUserDAO;
import pl.edu.wszib.model.User;
import pl.edu.wszib.services.IAuthenticationService;
import pl.edu.wszib.services.IUserService;
import src.pl.edu.wszib.configuration.AppConfigurationTest;

import java.util.ArrayList;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
@WebMvcTest //aczyna udawac kontener http
public class AuthenticationServiceImplTest {

    @Autowired
    IAuthenticationService authenticationService;

    @MockBean
    IUserDAO userDAO;    // udaje obiekt userDAO, imitacja

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authenticateUserTest() {
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("mateusz");

        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(new User(1, "mateusz", "617f41f48d1d4f9c787aed6b0ebc6f7d", "mateusz", "mateusz", 23)); // mozna any()
        //kiedy zawola  to getUserByLogin to wysyla  thenReturn
        //przez to mozna tworzyc testy  z roznymi danymi
        boolean result = this.authenticationService.authenticateUser(user);

        //IUserDAO mojeDAO = Mockito.mock(IUserDAO.class); jezeli nie trzeba bean mozna tak
        Assert.assertTrue(result);
        verify(this.userDAO, times(1)).getUserByLogin("mateusz"); //ile raz wywolana
    }
    /// STUB  to  kiedy mamy logike
    @Test
    public void callLoginPage() throws Exception{
        String expectedErrorMessage = "";
        String expectedViewName = "loginForm";

        this.mockMvc.perform(get("/loginPage"))    //bildery zwraca this temu mozna caly czas wywolac
                .andExpect(status().isOk())   //jezeli 200 to okey
                .andExpect(view().name(expectedViewName))
                .andExpect(model().attribute("errorMessage", emptyString()/*comparesEqualTo(expectedErrorMessage)*/ ))
                .andExpect(model().attribute("userModel", notNullValue()));
    }
}
