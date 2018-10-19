package com.shnnny.notBlog.service;

import com.shnnny.notBlog.model.po.User;

public interface UserService {

    /**
     * 根据用户id查询人员信息
     * @param userId
     * @return
     */
   User queryUserById(Integer userId);

    /**
     * 模糊匹配登录名或者邮箱名称
     * @param userName
     * @param email
     * @return
     */
   User findByUserNameOrEmail(String userName,String email);

    /**
     * 根据邮箱查询人的信息
     * @param email
     * @return
     */
   User findByEmail(String email);

    /**
     * 根据登录名查询人的信息
     * @param userName
     * @return
     */
   User findByUserName(String userName);

    /**
     * 保存用户
     * @param user
     * @return
     */
   int  save(User user);

    /**
     * 忘记密码邮箱验证的时候进行链接的有效验证
     * @param outDate
     * @param validataCode
     * @param email
     * @return
     */
   int setOutDateAndValidataCode(String outDate,String validataCode,String email);

    /**
     * 设置新的密码
     * @param passWord
     * @param email
     * @return
     */
   int setNewPassword(String passWord,String email);

    /**
     * 重新设置登录名
     * @param userName
     * @param email
     * @return
     */
   int setUserName(String userName, String email);

    /**
     * 更新用户的信息
     * @param user
     * @return
     */
   int update(User user);
}
