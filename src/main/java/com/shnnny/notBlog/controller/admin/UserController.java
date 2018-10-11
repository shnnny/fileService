package com.shnnny.notBlog.controller.admin;

import com.shnnny.notBlog.model.dto.Result;
import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.controller.BaseController;
import com.shnnny.notBlog.util.ResultUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangzhh
 * @date 2018/10/10 15:40
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public Result hello() {
        long l = System.currentTimeMillis();
        return ResultUtils.success("hello spring security");
    }

   @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id")Integer id){

        User user = new User();


        return ResultUtils.success(user);
    }
}
