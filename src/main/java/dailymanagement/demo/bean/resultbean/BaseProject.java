package dailymanagement.demo.bean.resultbean;

import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 项目包含的基本信息
 * @date 2020/2/12 14:28
 */

public class BaseProject {
    private Integer pid;
    private String pname;
    /**
     * 图片url
     */
    private String image  ;
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage  () {
        return image  ;
    }

    public void setImage  (String image  ) {
        this.image   = image  ;
    }
}
