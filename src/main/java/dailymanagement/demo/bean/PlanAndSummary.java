package dailymanagement.demo.bean;

import java.util.Date;

/**
 * 计划与总结表
 * id：Id
 * unam：作者
 * writeTime：创建时间
 * updateTime：最后修改时间
 * summary：个人总结
 * plan：工作计划
 *
 */
public class PlanAndSummary {
    private Integer id;

    private String unam;

    private Date writeTime;

    private Date updateTime;

    private String summary;

    private String plan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnam() {
        return unam;
    }

    public void setUnam(String unam) {
        this.unam = unam == null ? null : unam.trim();
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }
}