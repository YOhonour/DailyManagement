package dailymanagement.demo.bean;

import java.util.Date;

/**
 * 会员资源表
 * vid：Id
 * vnam：名称
 * vaccount：账号
 * vpassword：密码
 * endTime：截止时间
 *
 */
public class Vip {
    private Integer vid;

    private String vnam;

    private String vaccount;

    private String vpassword;

    private Date endTime;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVnam() {
        return vnam;
    }

    public void setVnam(String vnam) {
        this.vnam = vnam == null ? null : vnam.trim();
    }

    public String getVaccount() {
        return vaccount;
    }

    public void setVaccount(String vaccount) {
        this.vaccount = vaccount == null ? null : vaccount.trim();
    }

    public String getVpassword() {
        return vpassword;
    }

    public void setVpassword(String vpassword) {
        this.vpassword = vpassword == null ? null : vpassword.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}