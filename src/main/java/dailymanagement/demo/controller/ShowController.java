package dailymanagement.demo.controller;

import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.Project;
import dailymanagement.demo.service.ProjectService;
import dailymanagement.demo.utils.JsonDateValueProcessor;
import dailymanagement.demo.utils.ResponseResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 成果展示部分
 */

@RestController
@RequestMapping("/api/show")
@UserLogin(required = true)
public class ShowController {


    @Autowired
    ProjectService projectService;

    @ResponseBody
    @RequestMapping("/hello")
    public String say(){
        return "hello";
    }

    /**
     * 显示所有在研项目
     * @return 项目list表
     */
    @GetMapping("/ing")
    public ResponseResult getIngProject() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

        List<Project> projects = projectService.getAll();
        List<Project> list = new LinkedList<>();

        //比较当前时间与endtime来区分在研项目和已结项目
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        Calendar c2 = Calendar.getInstance();
        for (Project project: projects){
            int result = -1;
            if (project.getCloseTime() != null){
                c2.setTime(project.getCloseTime());
                 result = c1.compareTo(c2);
            }
            if (result < 0){
                System.out.println(project.toString());
                list.add(project);
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        return ResponseResult.success(jsonArray);
    }

    /**
     * 显示所有已结项目
     * @return 项目名称
     */
    @GetMapping("/end")
    public ResponseResult getEndProject() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

        List<Project> projects = projectService.getAll();
        List<Project> list = new LinkedList<>();

        //比较当前时间与endtime来区分在研项目和已结项目
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        Calendar c2 = Calendar.getInstance();
        for (Project project: projects){
            int result = -1;
            if (project.getCloseTime() != null){
                c2.setTime(project.getCloseTime());
                result = c1.compareTo(c2);
            }
            if (result > 0){
                System.out.println(project.toString());
                list.add(project);
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        return ResponseResult.success(jsonArray);
    }



}
