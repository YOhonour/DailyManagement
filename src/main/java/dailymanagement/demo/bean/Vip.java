package dailymanagement.demo.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dailymanagement.demo.utils.CustomDateSerializer;
import org.springframework.stereotype.Component;

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
@Component
public class Vip {
    private Integer vid;

    private String vnam;

    private String vaccount;

    private String vpassword;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date endTime;

    private Integer typeid;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "vid=" + vid +
                ", vnam='" + vnam + '\'' +
                ", vaccount='" + vaccount + '\'' +
                ", vpassword='" + vpassword + '\'' +
                ", endTime=" + endTime +
                ", typeid=" + typeid +
                '}';
    }
}