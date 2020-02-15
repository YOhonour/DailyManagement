package dailymanagement.demo.utils;

import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.vo.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/10 15:11
 */
@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtUtil {

    private String secret;

    private long expire;

    private String header;

    /**
     * 用户名
     */
    public static String USER ="user";

    /**
     * 生成token
     * @param user 用户
     * @return
     */
    public String createToken (Userinfo user){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        HashMap<String, Object> map = new HashMap<>();

        TokenUser tokenUser = new TokenUser();
        tokenUser.setRealname(user.getRealname());
        tokenUser.setUanm(user.getUnam());
        map.put(USER,tokenUser);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }
    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpire() {
        return expire;
    }
    public void setExpire(long expire) {
        this.expire = expire;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}
