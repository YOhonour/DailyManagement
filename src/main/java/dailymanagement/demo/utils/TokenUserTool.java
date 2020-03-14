package dailymanagement.demo.utils;

import dailymanagement.demo.bean.vo.TokenUser;
import dailymanagement.demo.exception.MyException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/14 11:25
 */

public class TokenUserTool {
    /**
     * 获取 token 中的用户信息
     * @return
     */
    public static TokenUser getTokenUser(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        TokenUser tokenUser = (TokenUser) request.getAttribute(JwtUtil.USER);
        if(tokenUser == null){
            throw new MyException(Status.NOT_LOGIN);
        }
        return tokenUser;
    }

    /**
     * 设置 用户名
     * @param unam
     * @return
     */
    public static String checkAndGetUnam(String unam){
        if(unam == null || unam.isEmpty()) {
            TokenUser tokenUser = getTokenUser();
            unam = tokenUser.getUnam();
        }
        return unam;
    }
}
