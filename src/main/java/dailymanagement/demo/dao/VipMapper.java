package dailymanagement.demo.dao;

import dailymanagement.demo.bean.Vip;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VipMapper {
    int deleteByPrimaryKey(Integer vid);

    int insert(Vip record);

    int insertSelective(Vip record);

    Vip selectByPrimaryKey(Integer vid);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);

    List<Vip> getAll();

    List<Vip> selectByName(String bnam);
}