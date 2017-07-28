package com.billpayment.service;

import com.billpayment.dao.UserDao;
import com.billpayment.model.Login;
import com.billpayment.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.billpayment.model.User;

import org.apache.log4j.Logger;


public class UserServiceImpl implements UserService {

  private static final Logger logger = Logger.getLogger(UserServiceImpl.class);


  @Autowired
  public UserDao userDao;

  public void register(User user) {
    logger.debug("Information received: year "+ user.getYear() + " month "+ user.getMonth() + " day "+ user.getDay() + " username "+ user.getUsername() + " firstname "+ user.getFirstName() + " lastname "+ user.getLastName() + " location "+ user.getLocation());
    String date = user.getYear()+user.getMonth()+user.getDay();
    user.setDateOfBirth(DateUtil.convertToSqlDate(DateUtil.parseYYYYMMdd(date)));
    userDao.register(user);
  }

  public User validateUser(Login login) {
    logger.debug("Entry into validateUser with usename "+login.getUsername());
    return userDao.validateUser(login);
  }

  public User findUser(String username) {
    logger.debug("Entry into findUser with usename "+username);
    return userDao.findUser(username);
  }

  public void modifyUser(User user) {
    logger.debug("Updating user "+ user.getUsername());
    userDao.updateUser(user);
    logger.debug("Updated user "+ user.getUsername());
  }

  public void updatePassword(String username, String password) {
    logger.debug("Updating user's password for "+ username);
    userDao.updatePassword(username, password);
    logger.debug("Updated user's password for "+ username);
  }
}
