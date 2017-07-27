package com.billpayment.dao;

import com.billpayment.model.Login;
import com.billpayment.model.User;

public interface UserDao {

  void register(User user);

  User validateUser(Login login);
}
