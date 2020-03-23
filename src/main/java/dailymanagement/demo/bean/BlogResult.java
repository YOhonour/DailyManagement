package dailymanagement.demo.bean;

import java.util.List;

public class BlogResult {

    private Blog blog;

    private boolean islike;

    private boolean iscollection;

    private List<String> userinfo;

    public List<String> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(List<String> userinfo) {
        this.userinfo = userinfo;
    }

    public BlogResult(Blog blog, boolean islike, boolean iscollection, List<String> userinfo) {
        this.blog = blog;
        this.userinfo = userinfo;
        this.islike = islike;
        this.iscollection = iscollection;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }

    public boolean isIscollection() {
        return iscollection;
    }

    public void setIscollection(boolean iscollection) {
        this.iscollection = iscollection;
    }
}
