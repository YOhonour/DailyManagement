package dailymanagement.demo.bean;

public class BrainChat {
    private Integer cid;

    private Integer brainid;

    private String comment;

    private Integer from;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getBrainid() {
        return brainid;
    }

    public void setBrainid(Integer brainid) {
        this.brainid = brainid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public BrainChat(Integer brainid, String comment, Integer from) {
        this.brainid = brainid;
        this.comment = comment;
        this.from = from;
    }

    public BrainChat(Integer cid, Integer brainid, String comment, Integer from) {
        this.cid = cid;
        this.brainid = brainid;
        this.comment = comment;
        this.from = from;
    }
}