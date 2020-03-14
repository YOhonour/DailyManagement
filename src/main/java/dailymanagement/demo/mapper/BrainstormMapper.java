package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.Brainstorm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface BrainstormMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Brainstorm record);

    int insertSelective(Brainstorm record);

    Brainstorm selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Brainstorm record);

    int updateByPrimaryKey(Brainstorm record);

    List<Brainstorm> getall();
}