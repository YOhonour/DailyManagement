package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.Vip;

public interface VipMapper {
    int deleteByPrimaryKey(Integer vid);

    int insert(Vip record);

    int insertSelective(Vip record);

    Vip selectByPrimaryKey(Integer vid);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);
}