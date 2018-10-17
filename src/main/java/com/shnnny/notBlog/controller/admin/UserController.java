package com.shnnny.notBlog.controller.admin;

import com.shnnny.notBlog.comm.CommGlobal;
import com.shnnny.notBlog.comm.aop.LoggerManage;
import com.shnnny.notBlog.controller.AbstractWebController;
import com.shnnny.notBlog.model.dto.Result;
import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.model.result.ExceptionMessage;
import com.shnnny.notBlog.service.UserService;
import com.shnnny.notBlog.util.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangzhh
 * @date 2018/10/10 15:40
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractWebController {

    private UserService userService;

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


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description="登陆")
    public Result login(User user, HttpServletResponse response) {
        try {
            User loginUser = userService.findByUserNameOrEmail(user.getUserName(), user.getUserName());

            if(loginUser == null){
                return ResultUtils.error(ExceptionMessage.LoginNameNotExists);
            }else if(!loginUser.getPassWord().equals(getPwd(user.getPassWord()))){
                return ResultUtils.error(ExceptionMessage.LoginNameOrPassWordError);
            }

            Cookie cookie = new Cookie(CommGlobal.LOGIN_SESSION_KEY, cookieSign(loginUser.getUserId().toString()));
            cookie.setMaxAge(CommGlobal.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResultUtils.success();
        }catch (Exception e){
            LOGGER.error("login failed" ,e);
            return ResultUtils.error("401","拒绝登录");
        }


    }
}
