package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User , Integer> {

   User findByUserNameOrEmail(String userName,String email);
   User findByUserName(String userName);
   User findByEmail(String email);

   @Modifying(clearAutomatically=true)
   @Transactional
   @Query("update User set outDate=:outDate, validataCode=:validataCode where email=:email")
   int setOutDateAndValidataCode(@Param("outDate") String outDate, @Param("validataCode") String validataCode, @Param("email") String email);

   @Modifying(clearAutomatically=true)
   @Transactional
   @Query("update User set passWord=:passWord where email=:email")
   int setNewPassword(@Param("passWord") String passWord, @Param("email") String email);
}
