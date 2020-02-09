package dailymanagement.demo.bean;

import java.util.Date;

/**
 * 用户信息表
 * unam：用户名
 * realname：姓名
 * sex：性别
 * upath：头像地址
 * school：学院
 * major：专业
 * birthday：生日
 * jointime：加入时间
 * prjHistory：项目经历
 * skills：自身技能
 * title：证书荣誉
 * phone：电话
 * qq：QQ
 * weibo：微博
 * mail：邮箱
 * password：密码
 *
 */
public class Userinfo {
    private String unam;

    private String realname;

    private String sex;

    private String upath;

    private String school;

    private String major;

    private Date birthday;

    private Date jointime;

    private String prjHistory;

    private String skills;

    private String title;

    private String phone;

    private String qq;

    private String weibo;

    private String mail;

    private String password;

    public String getUnam() {
        return unam;
    }

    public void setUnam(String unam) {
        this.unam = unam == null ? null : unam.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getUpath() {
        return upath;
    }

    public void setUpath(String upath) {
        this.upath = upath == null ? null : upath.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public String getPrjHistory() {
        return prjHistory;
    }

    public void setPrjHistory(String prjHistory) {
        this.prjHistory = prjHistory == null ? null : prjHistory.trim();
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills == null ? null : skills.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}