package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.Project;
import dailymanagement.demo.bean.resultbean.BaseProject;
import dailymanagement.demo.bean.vo.ProjectDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer pid);

    /**
     * 更新部分项目信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Project record);

    /**
     * 更新全部项目信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Project record);

    /**
     * 插入项目成员
     * @param project
     */
    void insertMembers(Project project);

    /**
     * 更新文件表的pid
     * @param projectDoc
     */
    void updateProjectDoc(ProjectDoc projectDoc);

    /**
     * 获取用户所有的项目
     * @param username
     * @return
     */
    List<BaseProject> getUserProjects(@Param("username") String username);

    /**
     * 获取项目的所有成员
     * @param pid
     * @return
     */
    List<String> getProjectMember(Integer pid);

    /**
     * 删除项目成员
     * @param project
     */
    void deleteMembers(Project project);
}