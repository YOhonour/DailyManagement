package dailymanagement.demo.controller;

import dailymanagement.demo.bean.Book;
import dailymanagement.demo.bean.Project;
import dailymanagement.demo.dao.BookMapper;
import dailymanagement.demo.dao.ProjectMapper;
import dailymanagement.demo.service.ProjectService;
import dailymanagement.demo.utils.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 成果展示部分
 */

@RestController
@RequestMapping("show")
@CrossOrigin(origins = "*", maxAge = 3600)
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
    public String getIngProject(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();

        List<Project> projects = projectService.getAll();
        List<Project> list = new LinkedList<>();

        //比较当前时间与endtime来区分在研项目和已结项目
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        Calendar c2 = Calendar.getInstance();
        for (Project project: projects){
            c2.setTime(project.getCloseTime());
            int result = c1.compareTo(c2);
            if (result < 0){
                System.out.println(project.toString());
                list.add(project);
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("ing",list);
        return jsonObject.toString();
    }

    /**
     * 显示所有已结项目
     * @return 项目名称
     */
    @GetMapping("/end")
    public String getEndProject(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();

        List<Project> projects = projectService.getAll();
        List<Project> list = new LinkedList<>();

        //比较当前时间与endtime来区分在研项目和已结项目
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        Calendar c2 = Calendar.getInstance();
        for (Project project: projects){
            c2.setTime(project.getCloseTime());
            int result = c1.compareTo(c2);
            if (result > 0){
                System.out.println(project.toString());
                list.add(project);
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("end",list);
        return jsonObject.toString();
    }



}
