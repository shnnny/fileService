package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.model.po.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleContentRepository extends JpaRepository<ArticleContent,Integer> {
}
