package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.model.po.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
