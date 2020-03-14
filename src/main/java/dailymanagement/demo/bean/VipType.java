package dailymanagement.demo.bean;

import java.util.List;

public class VipType {
    private Integer vtid;

    private String vtname;

    private String vtpath;

    public Integer getVtid() {
        return vtid;
    }

    public void setVtid(Integer vtid) {
        this.vtid = vtid;
    }

    public String getVtname() {
        return vtname;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname == null ? null : vtname.trim();
    }

    public String getVtpath() {
        return vtpath;
    }

    public void setVtpath(String vtpath) {
        this.vtpath = vtpath == null ? null : vtpath.trim();
    }

    @Override
    public String toString() {
        return "VipType{" +
                "vtid=" + vtid +
                ", vtname='" + vtname + '\'' +
                ", vtpath='" + vtpath + '\'' +
                '}';
    }
}