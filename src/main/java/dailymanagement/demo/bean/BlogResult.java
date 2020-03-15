package dailymanagement.demo.bean;

public class BlogResult {

    private Blog blog;

    private boolean islike;

    private boolean iscollection;

    public BlogResult(Blog blog, boolean islike, boolean iscollection) {
        this.blog = blog;
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
