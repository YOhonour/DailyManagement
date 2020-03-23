package dailymanagement.demo.service;

import dailymanagement.demo.bean.Blog;
import dailymanagement.demo.bean.Book;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.UserinfoCollection;
import dailymanagement.demo.mapper.BlogMapper;
import dailymanagement.demo.mapper.UserinfoCollectionMapper;
import dailymanagement.demo.mapper.UserinfoMapper;
import dailymanagement.demo.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BlogService implements BlogServiceImpl {
    @Autowired
    UserinfoMapper userinfoMapper;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserinfoCollectionMapper userinfoCollectionMapper;
    @Autowired
    BlogServiceImpl blogServiceImpl;

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
        if (model == 1) {
            return blogMapper.like(blogId);
        } else if (model == -1) {
            return blogMapper.cancelLike(blogId);
        } else {
            return 0;
        }
    }

    @Override
    public int collection(int blogId, int userId) {
        UserinfoCollection userinfoCollection = new UserinfoCollection(userId, blogId);
        return userinfoCollectionMapper.insert(userinfoCollection);
    }

    @Override
    public int collectioncancel(int userId, int blogId) {
        return userinfoCollectionMapper.deleteByuseridAndBlogid(userId, blogId);
    }

    @Override
    public int iscollection(int blogId, int userId) {
        UserinfoCollection userinfoCollection = userinfoCollectionMapper.selectByuseridAndBlogid(userId, blogId);
        if (userinfoCollection != null) {
            return 1;//收藏了
        }
        return 0;//没收藏
    }


    @Override
    public int publishBlog(int userId, Blog blog) {
        return blogMapper.insert(blog);
    }

    @Override
    public int finduserid(String name) {
        Userinfo userinfo = userinfoMapper.selectByUserName(name);
        System.out.println(userinfo);
        if (userinfo != null) {
            return userinfo.getUid();
        }

        return -1;
    }

    @Override
    public List<Blog> blogUserCollection(int userId) {
        List<UserinfoCollection> list = userinfoCollectionMapper.selectBlogidByUserid(userId);
        LinkedList<Blog> blogs = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            UserinfoCollection userinfoCollection = list.get(i);
            Integer blogid = userinfoCollection.getBlogid();
            Blog blog = blogMapper.selectByPrimaryKey(blogid);
            blogs.add(blog);

        }
        return blogs;
    }

    @Override
    public List<Blog> getblogRankingList() {
        return blogMapper.selectranklist();
    }

    @Override
    public List<String> findUserNameAndPhoto(int id) {
        List<String> list = new ArrayList<>();
        Userinfo userinfo = userinfoMapper.selectByuid(id);
        if (userinfo != null) {
            list.add(userinfo.getUnam());
            list.add(userinfo.getUpath());
        }
        return list;
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
