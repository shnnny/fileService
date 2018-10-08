package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.bean.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
