package com.shnnny.notBlog.utils;

import com.shnnny.notBlog.exception.Result;

/**
 * Created by zzh on 2018/9/21.
 */
public class ResultUtils {

    public static Result success(Object object){

        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);

        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }
}
