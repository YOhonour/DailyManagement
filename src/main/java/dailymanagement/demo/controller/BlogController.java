package dailymanagement.demo.controller;

import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.*;
import dailymanagement.demo.service.BlogService;
import dailymanagement.demo.service.BrainstormService;
import dailymanagement.demo.service.LikeService;
import dailymanagement.demo.service.impl.BrainChatServiceImpl;
import dailymanagement.demo.service.impl.FileService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@UserLogin(required = true)
@RequestMapping("/api")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    BrainstormService brainstormService;

    @Autowired
    BrainChatServiceImpl brainChatServiceImpl;
    @Autowired
    FileService fileService;
    @Autowired
    LikeService likeService;

    @GetMapping("/blogs")
    @ResponseBody
    @ApiOperation(value = "获取所有博客", notes = "1,name 用户名字")
    public String getAllBlog(HttpSession session, String name) {
        int userid = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getall();
        List<BlogResult> resultlist = new LinkedList();
        for (Blog blog : list) {
            boolean collection = false;
            int i = blogService.iscollection(blog.getBid(), userid);
            if (i == 1) {
                collection = true;
            }
            List<String> userinfo = blogService.findUserNameAndPhoto(blog.getAuthorid());
            int islike = likeService.findEntityLikeStatus(userid, blog.getBid());
            resultlist.add(new BlogResult(blog, islike == 1, collection, userinfo));
        }
        session.setAttribute("bloglist", list);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", resultlist);
        return jsonObject.toString();
    }

    @GetMapping("/blogs/by/type")
    @ResponseBody
    @ApiOperation(value = "获取所有博客 通过类型筛选", notes = "参数： <br>1、类型 如 产品 <br>")
    public String getAllBlogByType( String type, String name) {
        int userid = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getallbytypeid(type);
        List<BlogResult> resultlist = new LinkedList();
        for (Blog blog : list) {
            boolean collection = false;
            int i = blogService.iscollection(blog.getBid(), userid);
            if (i == 1) {
                collection = true;
            }
            List<String> userinfo = blogService.findUserNameAndPhoto(blog.getAuthorid());
            int islike = likeService.findEntityLikeStatus(userid, blog.getBid());
            resultlist.add(new BlogResult(blog, islike == 1, collection, userinfo));
        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", resultlist);
        return jsonObject.toString();
    }

    @GetMapping("/blogs/by/userid")
    @ResponseBody
    @ApiOperation(value = "获取用户相关博客", notes = "参数： <br>1、name 用户名字<br>")
    public String getAllBlogByUserId(HttpSession session, String name) {
        int userId = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getallblogbyuserid(userId);
        List<BlogResult> resultlist = new LinkedList();
        for (Blog blog : list) {
            boolean collection = false;
            int i = blogService.iscollection(blog.getBid(), userId);
            if (i == 1) {
                collection = true;
            }
            List<String> userinfo = blogService.findUserNameAndPhoto(blog.getAuthorid());
            int islike = likeService.findEntityLikeStatus(userId, blog.getBid());
            resultlist.add(new BlogResult(blog, islike == 1, collection, userinfo));
        }
        session.setAttribute("bloglist", list);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", resultlist);
        return jsonObject.toString();
    }


    @PostMapping("/blog/publish")
    @ResponseBody
    @ApiOperation(value = "发布博客", notes = "参数： <br>1、name 用户名字2,blog 类型 3,博客内容 comment  4,MultipartFile file 上传的图片 <br>")
    public String pubulishBlog(HttpSession session, String name, String type, String comment,
                               @RequestParam("file") MultipartFile file, String url2, String introduce) {
        int userId = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        Blog blog = new Blog();
        blog.setType(type);
        blog.setComment(comment);
        String url = fileService.upload(file);
        blog.setFilepath(url);
        blog.setAuthorid(userId);
        blog.setUrl(url2);
        blog.setIntroduce(introduce);
        int result = blogService.publishBlog(userId, blog);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "博客发布成功");
        return jsonObject.toString();
    }


    @PostMapping("/blog/like")
    @ResponseBody
    @ApiOperation(value = "点赞博客", notes = "参数： <br>1、博客id  2 ,model: 模式 赞还是取消赞  1 代表点赞 -1 代表取消赞<br>")
    public String bloglike(HttpSession session, int blogId, int model, String name) {//model 1 代表点赞 -1 代表取消赞
        JSONObject jsonObject = new JSONObject();
        int userId = blogService.finduserid(name);//redis
        likeService.like(userId, blogId, model);
        int i = blogService.like(blogId, model);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "操作成功");
        return jsonObject.toString();
    }

//    @PostMapping("/blog/islike")
//    @ResponseBody
//    @ApiOperation(value = "用户是否点赞", notes = "参数： <br>1、博客id  2 ,model: 模式 赞还是取消赞  1 代表点赞 -1 代表取消赞<br>")
//    public String bloglike(HttpSession session, int blogId, int ) {//model 1 代表点赞 -1 代表取消赞
//        JSONObject jsonObject = new JSONObject();
//        int i = blogService.like(blogId, model);
//        jsonObject.put("code", "200");
//        jsonObject.put("message", "success");
//        jsonObject.put("data", "操作成功");
//        return jsonObject.toString();
//    }

    @PostMapping("/blog/collection")
    @ResponseBody
    @ApiOperation(value = "收藏博客", notes = "参数： <br>1、博客id  2,name 用户名字 <br>")
    public String blogCollection(HttpSession session, int blogId, String name) {
        int userId = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        int i = blogService.collection(blogId, userId);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "收藏成功");
        return jsonObject.toString();
    }


    @GetMapping("/blog/iscollection")
    @ResponseBody
    @ApiOperation(value = "是否收藏博客", notes = "参数： <br>1、博客id  2name 用户名字 <br>")
    public String blogIsCollection(HttpSession session, int blogId, String name) {
        int userId = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        int i = blogService.iscollection(blogId, userId);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        if (i == 1) {
            jsonObject.put("data", "该用户收藏了此博客");
        } else if (i == 0) {
            jsonObject.put("data", "该用户没有收藏此博客");
        }
        return jsonObject.toString();
    }


    @PostMapping("/blog/collection/cancel")
    @ResponseBody
    @ApiOperation(value = "取消博客收藏", notes = "参数： <br>1、博客id 2,name 用户名字<br>")
    public String blogCollectionCancel(HttpSession session, int blogId, String name) {
        int userId = blogService.finduserid(name);
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

    @PostMapping("/blog/user/collection")
    @ResponseBody
    @ApiOperation(value = "用户博客收藏列表", notes = "参数： <br>1、博客id 2, name 用户名字<br>")
    public String blogUserCollection(HttpSession session, String name) {
        int userId = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.blogUserCollection(userId);
        List<BlogResult> resultlist = new LinkedList();
        for (Blog blog : list) {
            boolean collection = false;
            int i = blogService.iscollection(blog.getBid(), userId);
            if (i == 1) {
                collection = true;
            }
            List<String> userinfo = blogService.findUserNameAndPhoto(blog.getAuthorid());
            int islike = likeService.findEntityLikeStatus(userId, blog.getBid());
            resultlist.add(new BlogResult(blog, islike == 1, collection, userinfo));
        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", resultlist);
        return jsonObject.toString();
    }


    @PostMapping("/brainstorm/publish")
    @ResponseBody
    @ApiOperation(value = "发布头脑风暴", notes = "参数： <br>1、头脑风暴标题 2,name 用户名字/<br>")
    public String brainstormPublish(HttpSession session, String title, String name) {
        int userid = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        int i = brainstormService.publish(title, userid);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "发布idea成功");
        return jsonObject.toString();
    }


    @GetMapping("/brainstorms")
    @ResponseBody
    @ApiOperation(value = "获取所有头脑风暴", notes = "参数： <br><br>")
    public String brainstorm(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        List<Brainstorm> list = brainstormService.getall();
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", list);
        return jsonObject.toString();
    }


    @PostMapping("/brainchat/publish")
    @ResponseBody
    @ApiOperation(value = "发布头脑风暴的聊天内容", notes = "参数： <br>1、头脑风暴id 2,聊天内容 3,name 用户名字<br>")
    public String brainchatPublish(HttpSession session, int brainid, String comment, String name) {
        int userid = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        int i = brainChatServiceImpl.brainchatPublish(brainid, comment, userid);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "发表评论成功");
        return jsonObject.toString();
    }

    @GetMapping("/brainchats/bybrainid")
    @ResponseBody
    @ApiOperation(value = "获取头脑风暴的所有聊天内容", notes = "参数： <br>1、头脑风暴id <br>")
    public String brainchatsBybrainid(HttpSession session, int brainid) {
        JSONObject jsonObject = new JSONObject();
        List<BrainChat> list = brainChatServiceImpl.brainchatsBybrainid(brainid);
        List<BrainChatResult> listresult = new LinkedList<>();
        for (BrainChat chat : list) {
            List<String> userinfo = blogService.findUserNameAndPhoto(chat.getFrom());
            BrainChatResult brainChat = new BrainChatResult(chat, userinfo);
            listresult.add(brainChat);
        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", listresult);

        return jsonObject.toString();
    }


    @PostMapping("/blogs/rankinglist")
    @ResponseBody
    @ApiOperation(value = "排行榜接口", notes = "参数： <br><br>")
    public String blogRankingList(String name) {
        int userid = blogService.finduserid(name);
        JSONObject jsonObject = new JSONObject();
        List<Blog> list = blogService.getblogRankingList();
        List<BlogResult> resultlist = new LinkedList();
        for (Blog blog : list) {
            boolean collection = false;
            int i = blogService.iscollection(blog.getBid(), userid);
            if (i == 1) {
                collection = true;
            }
            List<String> userinfo = blogService.findUserNameAndPhoto(blog.getAuthorid());
            int islike = likeService.findEntityLikeStatus(userid, blog.getBid());
            resultlist.add(new BlogResult(blog, islike == 1, collection, userinfo));
        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", resultlist);
        return jsonObject.toString();
    }


    @PostMapping("/finduserid")
    @ResponseBody
    @ApiOperation(value = "找到用户id", notes = "参数： <br><br>")
    public String findIdByName(String name) {
        JSONObject jsonObject = new JSONObject();
        int id = blogService.finduserid(name);
        System.out.println(id);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", id);
        return jsonObject.toString();
    }


    @PostMapping("/find/nameandphoto")
    @ResponseBody
    @ApiOperation(value = "找到用户名字和头像", notes = "参数： <br>id<br>")
    public String findUserNameAndPhoto(int id) {
        JSONObject jsonObject = new JSONObject();
        List<String> userinfo = blogService.findUserNameAndPhoto(id);
        System.out.println(userinfo.get(0) + userinfo.get(1));
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", userinfo);
        return jsonObject.toString();
    }


    @PostMapping("/test")
    @ResponseBody
    @ApiOperation(value = "接口测试", notes = "参数： <br><br>")
    public String test(Blog blog) {
        JSONObject jsonObject = new JSONObject();
        System.out.println(blog);
        jsonObject.put("code", "200");
        jsonObject.put("message", "success");
        jsonObject.put("data", "成功");
        return jsonObject.toString();
    }
}
