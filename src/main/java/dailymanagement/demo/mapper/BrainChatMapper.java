package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.BrainChat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface BrainChatMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(BrainChat record);

    int insertSelective(BrainChat record);

    BrainChat selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(BrainChat record);

    int updateByPrimaryKey(BrainChat record);

    List<BrainChat> selectBybrainid(int brainid);
}