package dailymanagement.demo.controller;

import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.service.UserService;
import dailymanagement.demo.utils.ResponseResult;
import dailymanagement.demo.utils.Status;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  登陆相关业务
 * @date 2020/2/8 11:34
 */
@RestController
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger("ExceptinAspect");

    @Autowired
    private UserService userService;


    /**
     * 登陆
     * @param username
     * @param password
     * @return
     * @throws MyException
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public ResponseResult login(@RequestParam @ApiParam(value = "用户名",required = true) String username,
                        @RequestParam @ApiParam(value = "密码",required = true) String password) {

        Userinfo user = userService.login(username, password);
        if(user != null){
            //登陆成功
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            //在sessin中保存用户
            request.getSession().setAttribute("user",user);
            logger.info(username+"登陆成功");
            return ResponseResult.success();
        }else{
            logger.error("用户名"+username);
            logger.error("密码"+password);
            return ResponseResult.failure(Status.FAULT_PASSWORD);
        }
    }

    @ApiOperation("退出")
    @PostMapping("/loginOut")
    @UserLogin
    public ResponseResult logout(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        Userinfo user = (Userinfo)request.getSession().getAttribute("user");
        logger.info(user.getUnam()+"退出成功");
        request.getSession().removeAttribute("user");
        return ResponseResult.success(user.getUnam());
    }



}
