package dailymanagement.demo.bean;

public class Brainstorm {
    private Integer tid;

    private String title;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Brainstorm(String title) {
        this.title = title;
    }

    public Brainstorm(Integer tid, String title) {
        this.tid = tid;
        this.title = title;
    }
}