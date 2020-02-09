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
public class Book {

    private Integer bid;

    private String bname;

    private String edition;

    private String introduction;

    private String status;

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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition == null ? null : edition.trim();
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

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", edition='" + edition + '\'' +
                ", introduction='" + introduction + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}