package dailymanagement.demo.bean.resultbean;

import java.util.Date;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  保存计划与总结的基本信息
 * @date 2020/2/8 19:30
 */

public class PandS {
    private Integer id;

    private Date writeTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }
}
