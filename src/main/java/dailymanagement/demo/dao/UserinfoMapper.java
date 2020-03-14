package dailymanagement.demo.dao;

import dailymanagement.demo.bean.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(String unam);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(String unam);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}