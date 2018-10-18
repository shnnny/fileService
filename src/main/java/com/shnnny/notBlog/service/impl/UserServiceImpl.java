package com.shnnny.notBlog.service.impl;

import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.repository.UserRepository;
import com.shnnny.notBlog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangzhh
 * @date 2018/10/11 19:56
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public User queryUserById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public User findByUserNameOrEmail(String userName, String email) {
        return  userRepository.findByUserNameOrEmail(userName,email);

    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public int save(User user) {
        User save = userRepository.save(user);
        return save.getUserId();
    }

    @Override
    public int setOutDateAndValidataCode(String outDate, String validataCode,String email) {
        return userRepository.setOutDateAndValidataCode(outDate,validataCode,email);
    }

    @Override
    public int setNewPassword(String passWord, String email) {
        return userRepository.setNewPassword(passWord,email);
    }
}
