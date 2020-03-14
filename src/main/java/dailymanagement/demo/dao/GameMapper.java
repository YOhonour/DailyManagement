package dailymanagement.demo.dao;

import dailymanagement.demo.bean.Game;

public interface GameMapper {
    int deleteByPrimaryKey(Integer gid);

    int insert(Game record);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
}