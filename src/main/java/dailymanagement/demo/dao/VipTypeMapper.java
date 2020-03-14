package dailymanagement.demo.dao;

import dailymanagement.demo.bean.VipType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VipTypeMapper {
    int deleteByPrimaryKey(Integer vtid);

    int insert(VipType record);

    int insertSelective(VipType record);

    VipType selectByPrimaryKey(Integer vtid);

    int updateByPrimaryKeySelective(VipType record);

    int updateByPrimaryKey(VipType record);

    List<VipType> getAll();
}