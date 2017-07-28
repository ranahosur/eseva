package com.billpayment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.billpayment.model.Login;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.billpayment.model.User;
import com.billpayment.service.UserService;

@Controller
public class LoginController {

  private static final Logger logger = Logger.getLogger(LoginController.class);
  @Autowired
  UserService userService;

  @RequestMapping(value = "/loginnew", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("loginnew");
    mav.addObject("login", new Login());

    return mav;
  }

  @RequestMapping(value = "/loginnew", method = RequestMethod.POST)
  public ModelAndView showLoginPost(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("loginnew");
    mav.addObject("login", new Login());

    return mav;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showLoginWelcome(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("loginnew");
    mav.addObject("login", new Login());

    return mav;
  }



  @RequestMapping(value = "/loginProcessNew", method = RequestMethod.POST)
  public ModelAndView loginProcessNew(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("login") Login login) {
    ModelAndView mav = null;

    User user = userService.validateUser(login);

    if (null != user) {
      logger.debug("User is validated , redirecting to Welcome for firstname "+ user.getFirstName() + " lastname "+ user.getLastName() + " houseNo "+ user.getHouseNo() + " street "+ user.getStreet() + " city "+ user.getCity());
      request.getSession().setAttribute("username",user.getUsername());
      mav = new ModelAndView("welcome");
      mav.addObject("user",user);
      mav.addObject("firstname", user.getFirstName());
      mav.addObject("lastname", user.getLastName());
      mav.addObject("houseNo", user.getHouseNo());
      mav.addObject("street", user.getStreet());
      mav.addObject("city", user.getCity());
      mav.addObject("username", user.getUsername());
    } else {
      mav = new ModelAndView("loginnew");
      logger.warn("User authentication failed, redirecting login page");
      mav.addObject("message", "Username or Password is wrong!!");
    }

    return mav;
  }

  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("login") Login login) {
    ModelAndView mav = null;
    logger.debug("Before invalidating Session's username : "+ request.getSession().getAttribute("username"));
    request.getSession().invalidate();
    logger.debug("After invalidating Session's username : "+ request.getSession().getAttribute("username"));

    mav = new ModelAndView("loginnew");
    mav.addObject("message", "You have been successfully logged out");
    mav.addObject("user",new Login());

    return mav;
  }

  @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
  public ModelAndView resetPassword(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("resetPassword");
    mav.addObject("login", new Login());

    return mav;
  }

  @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
  public ModelAndView resetPasswordVerify(HttpServletRequest request, HttpServletResponse response,
                                          @ModelAttribute("login") Login login) {
    String username = login.getUsername();
    logger.debug("Entry into resetPasswordVerify with username "+ username);
    User user = userService.findUser(username);
    if(user == null){
      logger.error("There is no username with "+ username);
      ModelAndView mav = new ModelAndView("resetPassword");
      mav.addObject("login", new Login());
      mav.addObject("message", "Please enter a valid User Id");
      return mav;
    }
    else {
      logger.debug("The username exists -  "+ username);
      ModelAndView mav = new ModelAndView("sentPassword");
      mav.addObject("email", user.getEmail());
      mav.addObject("message", "Password has been sent to your E-mail id: ");
      return mav;
    }



  }


}
