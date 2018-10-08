package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
