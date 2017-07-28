package com.billpayment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.billpayment.model.User;
import com.billpayment.service.UserService;

@Controller
public class RegistrationController {
  @Autowired
  public UserService userService;


  @RequestMapping(value = "/registernew", method = RequestMethod.GET)
  public ModelAndView showRegisterNew(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("registernew");
    mav.addObject("user", new User());

    return mav;
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.GET)
  public ModelAndView showDashboardGet(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("welcome");
    User user = userService.findUser(request.getSession().getAttribute("username").toString());
    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.POST)
  public ModelAndView showDashboardPost(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("welcome");
    User user = userService.findUser(request.getSession().getAttribute("username").toString());
    mav.addObject("user", user);
    return mav;
  }


  @RequestMapping(value = "/registerProcessNew", method = RequestMethod.POST)
  public ModelAndView addUserNew(HttpServletRequest request, HttpServletResponse response,
                              @ModelAttribute("user") User user) {

    userService.register(user);
    request.getSession().setAttribute("username",user.getUsername());
    return new ModelAndView("welcome", "firstname", user.getFirstName());
  }
}
