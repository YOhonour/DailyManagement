package dailymanagement.demo.service.impl;

import dailymanagement.demo.bean.Blog;
import dailymanagement.demo.bean.Book;

import java.util.List;

public interface BlogServiceImpl {
    List<Blog> getall ();

//    List<Blog> selectBlogs(int userId, int offset, int limit,int typeId);

    int insert(Blog record);

    int deleteByPrimaryKey(Integer bid);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);


    List<Blog> getallbytypeid(String  typeid);

    List<Blog> getallblogbyuserid(int userId);

    int like(int blogId, int model);

    int collection(int blogId, int userId);

    int collectioncancel(int userId, int blogId);


    int iscollection(int blogId, int userId);

    int publishBlog(int userId, Blog blog);

    int finduserid(String name);

    List<Blog> blogUserCollection(int userId);
}
