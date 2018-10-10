package com.shnnny.notBlog.service.impl;

import com.shnnny.notBlog.repository.ArticleContentRepository;
import com.shnnny.notBlog.service.ArticleContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangzhh
 * @date 2018/10/10 21:00
 */
@Service
public class ArticleContentServiceImpl implements ArticleContentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleContentServiceImpl.class);

    @Autowired
    private ArticleContentRepository articleContentRepository;
}
