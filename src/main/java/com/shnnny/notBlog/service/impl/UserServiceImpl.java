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


    public User queryUserById(Integer userId) {
        return userRepository.getOne(userId);
    }
}
