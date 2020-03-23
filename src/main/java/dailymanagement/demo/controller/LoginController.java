package dailymanagement.demo.controller;

import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.vo.Login;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.service.UserService;
import dailymanagement.demo.utils.JwtUtil;
import dailymanagement.demo.utils.ResponseResult;
import dailymanagement.demo.utils.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.HashMap;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  登陆相关业务
 * @date 2020/2/8 11:34
 */
@RestController
@Api(tags = "登陆相关")
@RequestMapping("/api")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 登陆
     * @return
     * @throws MyException
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody Login login) {

        Userinfo user = userService.login(login.getUsername(), login.getPassword());
        if(user != null){
            //登陆成功
            String token = jwtUtil.createToken(user);
            logger.info(login.getUsername()+"登陆成功");
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",token);
            return ResponseResult.success(token);
        }else{
            logger.error("用户名"+login.getUsername());
            logger.error("密码"+login.getPassword());
            return ResponseResult.failure(Status.FAULT_PASSWORD);
        }
    }

    /**
     * 在拦截器实现退出
     * 退出由前端控制
     * @return
     */
/*    @ApiOperation("退出")
    @PostMapping("/loginOut")
    @UserLogin
    public ResponseResult logout(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String unam = (String) request.getAttribute(JwtUtil.UNAM);
        logger.info(unam+"退出成功");
        return ResponseResult.success(unam);
    }*/

}
