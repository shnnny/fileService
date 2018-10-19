package com.shnnny.notBlog.controller.admin;

import com.shnnny.notBlog.comm.CommGlobal;
import com.shnnny.notBlog.comm.aop.LoggerManage;
import com.shnnny.notBlog.controller.AbstractWebController;
import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.model.result.ExceptionMessage;
import com.shnnny.notBlog.model.result.Result;
import com.shnnny.notBlog.model.result.ResultUtils;
import com.shnnny.notBlog.service.UserService;
import com.shnnny.notBlog.util.BlogUtils;
import com.shnnny.notBlog.util.MD5Utils;
import com.shnnny.notBlog.util.MessageUtils;
import com.shnnny.notBlog.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * @author zhangzhh
 * @date 2018/10/10 15:40
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractWebController {

    private UserService userService;

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.subject.forgotpassword}")
    private String mailSubject;
    @Value("${mail.content.forgotpassword}")
    private String mailContent;
    @Value("${forgotpassword.url}")
    private String forgotpasswordUrl;
    @Value("${static.url}")
    private String staticUrl;
    @Value("${file.profilepictures.url}")
    private String fileProfilepicturesUrl;
    @Value("${file.backgroundpictures.url}")
    private String fileBackgroundpicturesUrl;

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

            //如果设置记住密码生成的cookie
            BlogUtils.setCookie(response,loginUser.getUserId());

            getSession().setAttribute(CommGlobal.LOGIN_SESSION_KEY, loginUser);
            return ResultUtils.success();
        }catch (Exception e){
            LOGGER.error("login failed" ,e);
            return ResultUtils.error("401","拒绝登录");
        }


    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @LoggerManage(description="注册")
    public Result create(User user) {

        try{
            User registUser = userService.findByEmail(user.getEmail());
            if(null != registUser){
                return ResultUtils.error(ExceptionMessage.EmailUsed);
            }
            User userNameUser = userService.findByUserName(user.getUserName());
            if(null != userNameUser){
                return ResultUtils.error(ExceptionMessage.UserNameUsed);
            }
            user.setPassWord(getPwd(user.getPassWord()));
            user.setCreated(System.currentTimeMillis());
            userService.save(user);

            getSession().setAttribute(CommGlobal.LOGIN_SESSION_KEY,user);
            return ResultUtils.success();
        }catch (Exception e){

            LOGGER.error("create user failed," ,e);
            return ResultUtils.error(ExceptionMessage.FAILED);
        }

    }

    /**
     * 忘记密码-发送重置邮件
     * @param email
     * @return
     */
    @RequestMapping(value = "/sendForgotPasswordEmail", method = RequestMethod.POST)
    @LoggerManage(description="发送忘记密码邮件")
    public Result sendForgotPasswordEmail(String email) {
        try {
            User registUser = userService.findByEmail(email);
            if(null == registUser){
                return ResultUtils.error(ExceptionMessage.EmailNotRegister);
            }
            //密钥
            String securityKey =  UUIDUtils.UU64();
            // 30分钟后过期
            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);
            userService.setOutDateAndValidataCode(outDate+"",securityKey,email);
            long date = outDate.getTime() / 1000 * 1000;
            String key =email + "$" + date + "$" + securityKey;
            // 数字签名
            String digitalSignature = MD5Utils.encrypt(key);
            //发送邮件
            String resetPassHref = forgotpasswordUrl + "?sid="
                    + digitalSignature +"&email="+email;
            String emailContent = MessageUtils.getMessage(mailContent, resetPassHref);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject(mailSubject);
            helper.setText(emailContent, true);
            mailSender.send(mimeMessage);

            return ResultUtils.success();
        }catch (Exception e){
            // TODO: handle exception
            LOGGER.error("sendForgotPasswordEmail failed, ", e);
            return ResultUtils.error(ExceptionMessage.FAILED);
        }
    }

    /**
     * 忘记密码-设置新密码
     * @param newpwd
     * @param email
     * @param sid
     * @return
     */
    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
    @LoggerManage(description="设置新密码")
    public Result setNewPassword(String newpwd, String email, String sid) {
        try {
            User user = userService.findByEmail(email);
            Timestamp outDate = Timestamp.valueOf(user.getOutDate());
            //表示已经过期
            if(outDate.getTime() <= System.currentTimeMillis()){
                return ResultUtils.error(ExceptionMessage.LinkOutdated);
            }
            //数字签名
            String key = user.getEmail()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidataCode();
            String digitalSignature = MD5Utils.encrypt(key);
            if(!digitalSignature.equals(sid)) {
                return  ResultUtils.error(ExceptionMessage.LinkOutdated);
            }
            userService.setNewPassword(getPwd(newpwd), email);
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.error("setNewPassword failed, ", e);
            return ResultUtils.error(ExceptionMessage.FAILED);
        }
        return ResultUtils.success();
    }
    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @LoggerManage(description="修改密码")
    public Result updatePassword(String oldPassword, String newPassword) {
        try {
            User user = getUser();
            String password = user.getPassWord();
            String newpwd = getPwd(newPassword);
            if(password.equals(getPwd(oldPassword))){
               //这两个更新方法的道理一样，这里需要进行验证哪一种的方式有效而且效率比较高
                userService.setNewPassword(newpwd, user.getEmail());
                user.setPassWord(newpwd);
                userService.update(user);
                getSession().setAttribute(CommGlobal.LOGIN_SESSION_KEY, user);
            }else{
                return ResultUtils.error(ExceptionMessage.PassWordError);
            }
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.error("updatePassword failed, ", e);
            return ResultUtils.error(ExceptionMessage.FAILED);
        }
        return ResultUtils.success();
    }

    /**
     * 修改昵称
     * @param userName
     * @return
     */
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    @LoggerManage(description="修改昵称")
    public Result updateUserName(String userName) {
        try {
            User loginUser = getUser();
            if(userName.equals(loginUser.getUserName())){
                return ResultUtils.error(ExceptionMessage.UserNameSame);
            }
            User user = userService.findByUserName(userName);
            if(null != user && user.getUserName().equals(userName)){
                return ResultUtils.error(ExceptionMessage.UserNameUsed);
            }
            if(userName.length()>12){
                return ResultUtils.error(ExceptionMessage.UserNameLengthLimit);
            }
            userService.setUserName(userName, loginUser.getEmail());
            loginUser.setUserName(userName);
            getSession().setAttribute(CommGlobal.LOGIN_SESSION_KEY, loginUser);
            return ResultUtils.success(userName);
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.error("updateUserName failed, ", e);
            return ResultUtils.error(ExceptionMessage.FAILED);
        }
    }
}
