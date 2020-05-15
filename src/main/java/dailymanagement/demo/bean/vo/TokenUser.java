package dailymanagement.demo.bean.vo;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 放在token 里的用户信息
 * @date 2020/2/15 18:52
 */

public class TokenUser {

    private String unam;
    private String realname;

    public String getUnam() {
        return unam;
    }

    public void setUnam(String unam) {
        this.unam = unam;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "TokenUser{" +
                "unam='" + unam + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }
}
