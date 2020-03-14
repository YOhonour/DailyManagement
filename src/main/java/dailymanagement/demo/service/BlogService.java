package dailymanagement.demo.service;

import dailymanagement.demo.bean.Blog;
import dailymanagement.demo.bean.Book;
import dailymanagement.demo.bean.UserinfoCollection;
import dailymanagement.demo.dao.BlogMapper;
import dailymanagement.demo.dao.UserinfoCollectionMapper;
import dailymanagement.demo.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService  implements BlogServiceImpl {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserinfoCollectionMapper userinfoCollectionMapper;


    @Override
    public List<Blog> getall() {
        return blogMapper.getAll();
    }

    @Override
    public List<Blog> getallbytypeid(String typeid) {
        return blogMapper.getallbytypeid(typeid);
    }

    @Override
    public List<Blog> getallblogbyuserid(int userId) {
        return blogMapper.getallblogbyuserid(userId);
    }

    @Override
    public int like(int blogId, int model) {
        if(model==1){
            return blogMapper.like(blogId);
        }else if(model==-1) {
            return blogMapper.cancelLike(blogId);
        }else {
            return 0;
        }
    }

    @Override
    public int collection(int blogId, int userId) {
        UserinfoCollection userinfoCollection = new UserinfoCollection(userId, blogId);
        return  userinfoCollectionMapper.insert(userinfoCollection);
    }

    @Override
    public int collectioncancel(int userId, int blogId) {
        return userinfoCollectionMapper.deleteByuseridAndBlogid(userId,blogId);
    }

    @Override
    public int insert(Blog record) {
        return blogMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer bid) {
        return blogMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insertSelective(Blog record) {
        return blogMapper.insertSelective(record);
    }

    @Override
    public Blog selectByPrimaryKey(Integer bid) {
        return blogMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return blogMapper.updateByPrimaryKey(record);
    }

}
