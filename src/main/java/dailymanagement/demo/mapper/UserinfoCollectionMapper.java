package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.UserinfoCollection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserinfoCollectionMapper {
    int deleteByPrimaryKey(Integer ucid);

    int insert(UserinfoCollection record);

    int insertSelective(UserinfoCollection record);

    UserinfoCollection selectByPrimaryKey(Integer ucid);

    int updateByPrimaryKeySelective(UserinfoCollection record);

    int updateByPrimaryKey(UserinfoCollection record);

    int deleteByuseridAndBlogid(int userId, int blogId);
}