package dailymanagement.demo.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.chart.ValueAxis;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@ApiModel(description = "项目信息表")
/**
 * 项目表
 * pid：项目ID
 * pname：项目名
 * pRealname：牵头人
 * beginTime：创建时间
 * closeTime：结项时间
 * introduction：项目简介
 *
 */
@Component
public class Project {

    private Integer pid;

    @ApiModelProperty(value = "项目名称",required = true)
    private String pname;

    @ApiModelProperty(value = "项目牵头人",required = true)
    private String pRealname;

    @ApiModelProperty(value = "开始时间",required = true)
    private Date beginTime;

    @ApiModelProperty(value = "结项时间",required = false)
    private Date closeTime;

    @ApiModelProperty(value = "项目简介",required = true)
    private String introduction;

    @ApiModelProperty(value = "项目其他成员",required = true,dataType = "list")
    private List<String> members;

    @ApiModelProperty(value = "项目图片url",required = false)
    private String image;

    @ApiModelProperty(value = "项目进度",required = false)
    private Integer progress;
    private List<Game> game;

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
        this.pname = pname == null ? null : pname.trim();
    }

    public String getpRealname() {
        return pRealname;
    }

    public void setpRealname(String pRealname) {
        this.pRealname = pRealname == null ? null : pRealname.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }


    public List<Game> getGame() {
        return game;
    }

    public void setGame(List<Game> game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", pRealname='" + pRealname + '\'' +
                ", beginTime=" + beginTime +
                ", closeTime=" + closeTime +
                ", introduction='" + introduction + '\'' +
                ", members=" + members +
                ", image='" + image + '\'' +
                ", progress=" + progress +
                ", game=" + game +
                '}';
    }
}