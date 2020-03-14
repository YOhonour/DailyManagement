package dailymanagement.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 书籍借阅表
 * bid: Id
 * bname: 书名
 * edition: 版本
 * introduction: 简介
 * status: 借出状态(char=2)
 *
 */
@Component
public class Book {
    private Integer bid;

    private String bname;

    private String introduction;

    private String status;

    private String ipath;

    private String btype;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }


    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIpath() {
        return ipath;
    }

    public void setIpath(String ipath) {
        this.ipath = ipath;
    }


    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", introduction='" + introduction + '\'' +
                ", status='" + status + '\'' +
                ", ipath='" + ipath + '\'' +
                ", btype='" + btype + '\'' +
                '}';
    }
}