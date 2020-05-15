package dailymanagement.demo;

import dailymanagement.demo.bean.PlanAndSummary;
import dailymanagement.demo.bean.Project;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.vo.Login;
import dailymanagement.demo.bean.vo.PlatFormUser;
import dailymanagement.demo.bean.vo.TokenUser;
import dailymanagement.demo.controller.BlogController;
import dailymanagement.demo.controller.InfoShareController;
import dailymanagement.demo.controller.LoginController;
import dailymanagement.demo.controller.UserController;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.mapper.ProjectMapper;
import dailymanagement.demo.mapper.UserinfoMapper;
import dailymanagement.demo.utils.JwtUtil;
import dailymanagement.demo.utils.ResponseResult;
import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;
import org.apache.catalina.User;
import org.apache.http.HttpRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserController userController;

    @Autowired
    private InfoShareController infoShareController;

    @Autowired
    private LoginController loginController;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BlogController blogController;

    @Test
    void contextLoads() {
        List<PlatFormUser> user = userinfoMapper.getPlatformUser(null);
        user.forEach(o->{
            System.out.println(o.getRealname());
        });
    }

    @Test
    void updateTest() throws MyException {
//        Project project = new Project();
//        project.setBeginTime(new Date());
//        project.setImage("https://cn.bing.com/th?id=OHR.SiestaKey_ZH-CN1759696989_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");
//        project.setIntroduction("homework提交系统");
//        project.setPname("homework提交系统");
//        project.setpRealname("胡玉");
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("马云豪");
//        strings.add("王圆坤");
//        strings.add("刘琦");
//        strings.add("颜文翊");
//        strings.add("李雅萱");
//        strings.add("刘俣玲");
//        project.setMembers(strings);
//        project.setPid(29);
//        userController.updateProject(project);

//        Userinfo userinfo = new Userinfo();
//        userinfo.setUnam("lusi");
//        userinfo.setSkills("九阴白骨爪");
//        userController.updateUser(userinfo);
    }

    @Test
    void getTest(){
//        Project project = projectMapper.selectByPrimaryKey(6);
//        System.out.println(project.getProgress());
//        ResponseResult detail = userController.getUserProjectDetail(29);
//        System.out.println(detail.getData());
//        infoShareController.getBookType1(null);
        String blog = blogController.getAllBlogByType("服务器", "2018210340");
        System.out.println(blog);
    }


/*
    @Test
    void login(){
        Login login = new Login();
        login.setUsername("admin");
        login.setPassword("123456");
        loginController.login(login);
    }*/


/*    @Test
    void insertUser() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\11726\\Desktop\\chenyuan.csv"));
        Scanner in = new Scanner(inputStream);
        String unam ="";
        while (in.hasNext()){
            unam = in.next();
            System.out.println(unam);
            userinfoMapper.insertUser(unam);
        }
    }*/




}
