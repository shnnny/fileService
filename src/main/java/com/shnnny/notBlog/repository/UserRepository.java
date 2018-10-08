package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {
}
