package dailymanagement.demo.dao;

import dailymanagement.demo.bean.PlanAndSummary;
import org.apache.ibatis.annotations.Param;

public interface PlanAndSummaryMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("unam") String unam);

    int insert(PlanAndSummary record);

    int insertSelective(PlanAndSummary record);

    PlanAndSummary selectByPrimaryKey(@Param("id") Integer id, @Param("unam") String unam);

    int updateByPrimaryKeySelective(PlanAndSummary record);

    int updateByPrimaryKey(PlanAndSummary record);
}