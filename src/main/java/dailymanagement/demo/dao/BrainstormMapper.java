package dailymanagement.demo.dao;

import dailymanagement.demo.bean.Brainstorm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BrainstormMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Brainstorm record);

    int insertSelective(Brainstorm record);

    Brainstorm selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Brainstorm record);

    int updateByPrimaryKey(Brainstorm record);

    List<Brainstorm> getall();
}