package dailymanagement.demo.controller;

import dailymanagement.demo.bean.Blog;
import dailymanagement.demo.bean.Book;
import dailymanagement.demo.bean.BrainChat;
import dailymanagement.demo.bean.Brainstorm;
import dailymanagement.demo.service.BlogService;
import dailymanagement.demo.service.BrainstormService;
import dailymanagement.demo.service.impl.BrainChatServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    BrainstormService brainstormService;

    @Autowired
    BrainChatServiceImpl   brainChatServiceImpl;
    @GetMapping("/blogs")
    @ResponseBody
    @ApiOperation(value = "获取所有博客",notes = "")
    public String getAllBlog(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getall();
        for (Blog blog : list) {
            System.out.println(blog.toString());
        }
        session.setAttribute("bloglist", list);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", list);
        return jsonObject.toString();
    }

    @GetMapping("/blogs/by/typy")
    @ResponseBody
    @ApiOperation(value = "获取所有博客类型",notes = "参数： <br>1、类型的id<br>")
    public String getAllBlogByType(HttpSession session, String typeId) {
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getallbytypeid(typeId);
        for (Blog blog : list) {
            System.out.println(blog.toString());
        }
        session.setAttribute("bloglist", list);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", list);
        return jsonObject.toString();
    }

    @GetMapping("/blogs/by/userid")
    @ResponseBody
    @ApiOperation(value = "获取用户相关博客",notes = "参数： <br>1、用户id<br>")
    public String getAllBlogByUserId(HttpSession session, int userId) {
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getallblogbyuserid(userId);
        for (Blog blog : list) {
            System.out.println(blog.toString());
        }
        session.setAttribute("bloglist", list);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", list);
        return jsonObject.toString();
    }


    @PostMapping("/blog/like")
    @ResponseBody
    @ApiOperation(value = "收藏博客",notes = "参数： <br>1、博客id  2 ,model: 模式 赞还是取消赞  1 代表点赞 -1 代表取消赞<br>")
    public String bloglike(HttpSession session, int blogId, int model) {//model 1 代表点赞 -1 代表取消赞
        JSONObject jsonObject = new JSONObject();
        int i = blogService.like(blogId, model);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "操作成功");
        return jsonObject.toString();
    }


    @PostMapping("/blog/collection")
    @ResponseBody
    @ApiOperation(value = "收藏博客",notes = "参数： <br>1、博客id  2,用户id <br>")
    public String blogCollection(HttpSession session, int blogId, int userId) {
        System.out.println("blogid " + blogId + "--userid" + userId);
        JSONObject jsonObject = new JSONObject();
        int i = blogService.collection(blogId, userId);
        System.out.println(i);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "收藏成功");
        return jsonObject.toString();
    }


    @PostMapping("/blog/collection/cancel")
    @ResponseBody
    @ApiOperation(value = "取消博客收藏",notes = "参数： <br>1、博客id 2, 用户id<br>")
    public String blogCollectionCancel(HttpSession session, int blogId, int userId) {
        JSONObject jsonObject = new JSONObject();
        int i = blogService.collectioncancel(userId, blogId);
        if (i == 0) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "fail");
            jsonObject.put("data", "取消收藏失败");

        } else {
            System.out.println(i);
            jsonObject.put("code", "200");
            jsonObject.put("message", "success");
            jsonObject.put("data", "取消收藏成功");
        }
        return jsonObject.toString();
    }


    @PostMapping("/brainstorm/publish")
    @ResponseBody
    @ApiOperation(value = "发布头脑风暴",notes = "参数： <br>1、头脑风暴标题 2,用户id/<br>")
    public String brainstormPublish(HttpSession session, String title,int userid) {
        JSONObject jsonObject = new JSONObject();
        int i = brainstormService.publish(title,userid);
        System.out.println(i);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "发布idea成功");
        return jsonObject.toString();
    }


    @GetMapping("/brainstorms")
    @ResponseBody
    @ApiOperation(value = "获取所有头脑风暴",notes = "参数： <br><br>")
    public String brainstorm(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        List<Brainstorm> list=brainstormService.getall();
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", list);
        return jsonObject.toString();
    }


    @PostMapping("/brainchat/publish")
    @ResponseBody
    @ApiOperation(value ="发布头脑风暴的聊天内容" ,notes = "参数： <br>1、头脑风暴id 2,聊天内容 3,用户id<br>")
    public String brainchatPublish(HttpSession session,int brainid,String comment ,int userid) {
        JSONObject jsonObject = new JSONObject();
       int i= brainChatServiceImpl.brainchatPublish(brainid,comment,userid);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "发表评论成功");
        return jsonObject.toString();
    }

    @GetMapping("/brainchats/bybrainid")
    @ResponseBody
    @ApiOperation(value ="获取头脑风暴的所有聊天内容" ,notes = "参数： <br>1、头脑风暴id <br>")
    public String brainchatsBybrainid(HttpSession session,int brainid) {
        JSONObject jsonObject = new JSONObject();
        List <BrainChat> list= brainChatServiceImpl.brainchatsBybrainid(brainid);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data",list);
        return jsonObject.toString();
    }


    @PostMapping("/test")
    @ResponseBody
    @ApiOperation(value = "接口测试",notes = "参数： <br><br>")
    public String test(Blog blog) {
        JSONObject jsonObject = new JSONObject();
        System.out.println(blog);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "成功");
        return jsonObject.toString();
    }





}
