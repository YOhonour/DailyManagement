package dailymanagement.demo.bean;

public class Game {
    private Integer gid;

    private Integer pgid;

    private String gname;

    private String grecord;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getPgid() {
        return pgid;
    }

    public void setPgid(Integer pgid) {
        this.pgid = pgid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getGrecord() {
        return grecord;
    }

    public void setGrecord(String grecord) {
        this.grecord = grecord == null ? null : grecord.trim();
    }
}