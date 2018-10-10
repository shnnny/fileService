package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.model.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
