CREATE DATABASE IF NOT EXISTS billpayment;
USE billpayment;

DROP TABLE IF EXISTS `billpayment`.`user`;
CREATE TABLE `billpayment`.`user` (
   `user_id` VARCHAR(32) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `email` VARCHAR(80) NULL,
  `house_no` VARCHAR(45) NULL,
  `street` VARCHAR(80) NULL,
  `location` VARCHAR(120) NULL,
  `city` VARCHAR(80) NULL,
  `state` VARCHAR(2) NULL,
  `pin_code` VARCHAR(6) NULL,
  `residence_phone` VARCHAR(10) NULL,
  `mobile_phone` VARCHAR(10) NULL,
  `office_phone` VARCHAR(10) NULL,
  `category` VARCHAR(1) NULL,
  PRIMARY KEY (`user_id`));