package dailymanagement.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "项目文件")
public class DocumentFile {

    @ApiModelProperty(value = "项目文件id")
    private Integer fid;

    @ApiModelProperty(value = "文件名",required = false)
    private String fname;

    @ApiModelProperty(value = "上传时间",required = false)
    private Date time;

    @ApiModelProperty(value = "文件保存路径",required = false)
    private String fpath;

    @ApiModelProperty(value = "项目id",required = false)
    private Integer pid;

    @ApiModelProperty(value = "项目类型",required = true)
    private String doctype;

    @ApiModelProperty(value = "提交人姓名",required = false)
    private String dUnam;
    private String ftype;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getdUnam() {
        return dUnam;
    }

    public void setdUnam(String dUnam) {
        this.dUnam = dUnam;
    }
    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    @Override
    public String toString() {
        return "DocumentFile{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", time=" + time +
                ", fpath='" + fpath + '\'' +
                ", ftype='" + ftype + '\'' +
                '}';
    }
}