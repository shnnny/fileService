package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.bean.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Integer> {
}
