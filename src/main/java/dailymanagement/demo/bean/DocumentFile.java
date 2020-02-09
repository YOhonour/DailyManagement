package dailymanagement.demo.bean;

import java.util.Date;

/**
 * 项目文件表
 * fid：文件ID
 * fname：文件名
 * time：时间
 * fpath：文件物理路径
 *
 */
public class DocumentFile {
    private Integer fid;

    private String fname;

    private Date time;

    private String fpath;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath == null ? null : fpath.trim();
    }
}