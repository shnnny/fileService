package com.shnnny.notBlog.service;

import com.shnnny.notBlog.model.po.User;

public interface UserService {

   User queryUserById(Integer userId);
   User findByUserNameOrEmail(String userName,String email);
   User findByEmail(String email);
   User findByUserName(String userName);
   int  save(User user);

   int setOutDateAndValidataCode(String outDate,String validataCode,String email);

   int setNewPassword(String passWord,String email);

   int setUserName(String userName, String email);
}
