package dailymanagement.demo.dao;

import dailymanagement.demo.bean.BrainChat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BrainChatMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(BrainChat record);

    int insertSelective(BrainChat record);

    BrainChat selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(BrainChat record);

    int updateByPrimaryKey(BrainChat record);

    List<BrainChat> selectBybrainid(int brainid);
}