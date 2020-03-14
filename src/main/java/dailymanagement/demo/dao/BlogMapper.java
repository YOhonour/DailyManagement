package dailymanagement.demo.dao;

import dailymanagement.demo.bean.Blog;
import dailymanagement.demo.bean.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BlogMapper {

    int deleteByPrimaryKey(Integer bid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> getAll();

    List<Blog> getallbytypeid(String typeid);

    List<Blog> getallblogbyuserid(int userId);

    int like(int bid);

    int cancelLike(int bid);

    int colletion(int blogId, int userId);
}