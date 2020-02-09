package dailymanagement.demo.controller;

import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.DocumentType;
import dailymanagement.demo.bean.PlanAndSummary;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.resultbean.PandS;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.service.UserService;
import dailymanagement.demo.utils.ResponseResult;
import dailymanagement.demo.utils.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/8 15:13
 */
@RestController
@RequestMapping(value = "/user")
@UserLogin(required = true)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    //---------------------------------用户相关---------------------------------
    @ApiOperation("获取用户详细信息")
    @GetMapping("/getUserInfoByUnam")
    public ResponseResult getUserInfoByUnam(@RequestParam @ApiParam(value = "用户名", required = true) String username) {
        Userinfo user = userService.getUserInfoByUnam(username);
        if (user == null) {
            return ResponseResult.failure(Status.NotFound);
        }
        return ResponseResult.success(user);
    }

    /**
     * 添加或修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation("修改用户信息但不可修改密码")
    @PostMapping("/updateUser")
    public ResponseResult updateUser(@RequestBody @ApiParam(value = "用户信息", required = true) Userinfo user) throws MyException {
        if (user.getUnam() == null){
            Userinfo sUser = (Userinfo)httpSession.getAttribute("user");
            user.setUnam(sUser.getUnam());
        }
        userService.updateUser(user);
        return ResponseResult.success();
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public ResponseResult updatePassword(@RequestParam @ApiParam(value = "旧密码",name = "oldpw", required = true) String oldpw,
                                         @RequestParam @ApiParam(value = "新密码",name = "newpw",required = true) String newPw) throws MyException {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        userService.updatePassword(user.getUnam(), oldpw, newPw);
        return ResponseResult.success();
    }


    //---------------------------------计划与总结相关---------------------------------

    @ApiOperation("添加或修改工作计划与总结")
    @PostMapping("/updatePlanAndSummary")
    public ResponseResult updatePlanAndSummary(@RequestBody @ApiParam(value = "工作与总结信息", required = true) PlanAndSummary pas) {
        userService.updatePaS(pas);
        return ResponseResult.success();
    }

    @ApiOperation("获取用户的所有工作计划与总结")
    @GetMapping("/getUserPaSs")
    public ResponseResult getUserPlanAndSummarys(@RequestParam @ApiParam(value = "用户名", required = true) String username) {
        List<PandS> list = userService.getUserPaSs(username);
        return ResponseResult.success(list);
    }

    @ApiOperation("获取工作计划与总结的详细信息")
    @GetMapping("/{id}/getDetailPaS")
    public ResponseResult getDetailPaS(@PathVariable(required = true, value = "id") @ApiParam(value = "工作计划与总结id", required = true) Integer id,
                                       @RequestParam(required = false) @ApiParam(value = "用户名", required = false) String username) {
        PlanAndSummary paS = userService.getDetailPaS(id, username);
        return ResponseResult.success(paS);
    }

    //---------------------------------个人文档相关---------------------------------

    @ApiOperation("获取所有文件类型")
    @GetMapping("/getDocTypes")
    public ResponseResult getDocTypes() {
        List<DocumentType> doctypes = userService.getDocTypes();
        ArrayList<String> types = new ArrayList<>();
        int size = doctypes.size();
        for (int i = 0; i < size; i++) {
            types.add(doctypes.get(i).getDoctype());
        }
        return ResponseResult.success(types);
    }

/*    public ResponseResult updateDoc


    @ApiOperation("获取用户的所有文档")
    @GetMapping("/getUserDocs")
    public ResponseResult getUserDocs(@RequestParam @ApiParam(value = "用户名", required = true) String username) {
        userService.getUserDoc(username);
    }*/


}

