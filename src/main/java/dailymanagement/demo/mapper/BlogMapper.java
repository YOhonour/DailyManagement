package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.Blog;
import dailymanagement.demo.bean.Book;
import dailymanagement.demo.bean.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
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

    List<Blog> selectranklist();


}