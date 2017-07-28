package com.billpayment.service;

import com.billpayment.model.Login;
import com.billpayment.model.User;

public interface UserService {

  void register(User user);

  User validateUser(Login login);

  User findUser(String username);

  void modifyUser(User user);

  void updatePassword(String username, String password);

}
