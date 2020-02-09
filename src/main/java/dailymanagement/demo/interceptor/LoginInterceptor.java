package dailymanagement.demo.interceptor;

import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.utils.Status;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 登陆校验
 * @date 2020/2/8 11:02
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.判断是否存在注解
        if(!(handler instanceof HandlerMethod)){
            logger.info("不是HandlerMethod类型，则无需检查");
            return true;
        }
        HandlerMethod method = (HandlerMethod)handler;
        UserLogin classLoginAnn = method.getBeanType().getAnnotation(UserLogin.class);
        UserLogin methodLoginAnn = method.getMethod().getAnnotation(UserLogin.class);

        //校验是否需要登陆检验
        //检验对象
        //1.方法上有注解，且为require(优先)
        //2.类上有注解，且为require
        boolean needLogin = false;
        if (methodLoginAnn!=null){
            //方法上有注解
            needLogin = methodLoginAnn.required();
        }else if(classLoginAnn!=null){
            //类上有注解
            needLogin = classLoginAnn.required();
        }

        if (needLogin && request.getSession().getAttribute("user") ==null) {
            //如果需要检验，同时检验不通过
            response.sendError(Status.NOT_LOGIN.getCode(),Status.NOT_LOGIN.getMessage());
            logger.error("用户未登陆");
            return false;
        }
        return true;
    }
}
