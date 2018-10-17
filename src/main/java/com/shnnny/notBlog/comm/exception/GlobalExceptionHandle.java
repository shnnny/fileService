package com.shnnny.notBlog.comm.exception;

import com.shnnny.notBlog.model.dto.Result;
import com.shnnny.notBlog.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 * Created by zzh on 2018/9/21.
 */
public class GlobalExceptionHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandle(HttpServletRequest request, Exception ex){
        ex.printStackTrace();
        if (ex instanceof MissingServletRequestParameterException) {
            return ResultUtils.error("400", ex.toString());
        }
        if (ex instanceof TipException) {

            LOGGER.error("=======" + ex.getMessage() + "=======");
            return ResultUtils.error("400", "运行异常！");

        }

        if (ex instanceof DuplicateKeyException) {
            LOGGER.error("=======违反主键约束：主键重复插入=======");
            return ResultUtils.error("400", "主键重复插入！");
        }

        /**
         * 未知异常
         */
        LOGGER.error(ex.toString());
        return ResultUtils.error("500", ex.getMessage());

    }
}
