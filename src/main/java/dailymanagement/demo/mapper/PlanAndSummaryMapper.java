package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.PlanAndSummary;
import dailymanagement.demo.bean.resultbean.PandS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Component
@Mapper
public interface PlanAndSummaryMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("unam") String unam);

    int insert(PlanAndSummary record);

    int insertSelective(PlanAndSummary record);

    PlanAndSummary selectByPrimaryKey(@Param("id") Integer id, @Param("unam") String unam);

    int updateByPrimaryKeySelective(PlanAndSummary record);

    int updateByPrimaryKey(PlanAndSummary record);

    /**
     * 通过用户名获取用户所有工作计划与总结
     * @param username
     * @return
     */
    List<PandS> getUserPaSs(String username);
}