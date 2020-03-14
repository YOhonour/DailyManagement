package dailymanagement.demo.bean;

public class UserinfoCollection {
    private Integer ucid;

    private Integer userid;

    private Integer blogid;

    public Integer getUcid() {
        return ucid;
    }

    public void setUcid(Integer ucid) {
        this.ucid = ucid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public UserinfoCollection(Integer userid, Integer blogid) {
        this.userid = userid;
        this.blogid = blogid;
    }
}