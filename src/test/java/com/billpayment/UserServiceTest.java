package com.billpayment;

import static org.junit.Assert.assertEquals;

import com.billpayment.model.Login;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.billpayment.model.User;
import com.billpayment.service.UserService;

@ContextConfiguration(locations = { "classpath:/billpayment/config/user-beans.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Before
  public void setUp() throws Exception {
  }

//  @Test
  public void testRegisterUser() {

    User user = new User();
    System.out.println("test");
//    user.setAddress("213 test");
    user.setEmail("ranahosur@test.com");
    user.setFirstName("raghu");
    user.setLastName("An");
//    user.setPhone(123424);
    user.setUsername("raghu");
    user.setPassword("anahosur");

    userService.register(user);


  }

  @Test
  public void testValidateUser() {

    Login login = new Login();
    login.setUsername("raghu");
    login.setPassword("anahosur");

    User user = userService.validateUser(login);

//    assertEquals("raghu", user.getFirstName());
  }

}
