package dailymanagement.demo.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 项目文件
 * @date 2020/2/15 17:31
 */
@ApiModel(description = "项目文件信息 关联文件和项目")
public class ProjectDoc {
    @ApiModelProperty("项目的id")
    private Integer pid;
    @ApiModelProperty("文件id")
    private Integer fid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
