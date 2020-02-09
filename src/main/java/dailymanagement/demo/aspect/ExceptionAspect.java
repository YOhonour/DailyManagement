package dailymanagement.demo.aspect;

import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.utils.ResponseResult;
import dailymanagement.demo.utils.Status;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.security.provider.certpath.OCSPResponse;

import javax.xml.ws.Response;
import java.util.Arrays;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  异常处理，结果处理，日志打印
 * @date 2020/2/8 13:41
 */

@Aspect
@Component
public class ExceptionAspect {

    private final Logger logger = LoggerFactory.getLogger("ExceptinAspect");

    @Pointcut(value = "execution(* dailymanagement.demo.controller.UserController.*(..))")
    private void myPointcut() {
    }


    /**
     * 环绕通知 用于异常处理、结果处理和日志打印
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "ExceptionAspect.myPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String classname = proceedingJoinPoint.getTarget().getClass().getName();
        String methodname = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        logger.info("类:{},方法:{}",classname,methodname);
        logger.info("参数 {}", Arrays.toString(args));
        Object result = null;
        try {
            result =  proceedingJoinPoint.proceed();
            logger.info("执行成功");
            logger.info("返回 {}",result);
            return result;

        } catch (MyException e) {
            logger.error(methodname +"发生异常");
            logger.error(e.getStatus().getMessage());
            return ResponseResult.failure(e.getStatus());
        }catch (Exception e){
            logger.error(methodname +"发生异常");
            logger.error(e.getMessage());
            return ResponseResult.failure(Status.SysError);
        }
    }
}
