package com.shnnny.notBlog.service.impl;

import com.shnnny.notBlog.model.po.Log;
import com.shnnny.notBlog.repository.LogRepository;
import com.shnnny.notBlog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangzhh
 * @date 2018/10/16 17:23
 */
@Service
public class LogServiceImpl implements LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogRepository logRepository;

    @Override
    public void insertLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public void insertLog(String action, String data, String ip, Integer authorId) {

        Log logs = new Log();
        logs.setAction(action);
        logs.setData(data);
        logs.setIp(ip);
        logs.setAuthorId(authorId);
        logs.setCreated(System.currentTimeMillis());
        logRepository.save(logs);
    }

    @Override
    public Page<Log> getLogs(int page, int limit) {

        LOGGER.debug("Enter getLogs method:page={},linit={}",page,limit);
      /*  可以在接口进行默认设置
        if (page <= 0) {
            page = 1;
        }
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }*/
        Pageable pageable = PageRequest.of(page, limit);

        Page<Log> all = logRepository.findAll(pageable);
        LOGGER.debug("Exit getLogs method");
        return all;
    }
}
