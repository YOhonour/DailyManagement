package dailymanagement.demo.controller;

import com.sun.org.apache.xpath.internal.operations.Mult;
import dailymanagement.demo.annotation.UserLogin;
import dailymanagement.demo.bean.*;
import dailymanagement.demo.bean.resultbean.BaseProject;
import dailymanagement.demo.bean.resultbean.PandS;
import dailymanagement.demo.bean.vo.PlatFormUser;
import dailymanagement.demo.bean.vo.ProjectDoc;
import dailymanagement.demo.bean.vo.TokenUser;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.service.UserService;
import dailymanagement.demo.utils.JwtUtil;
import dailymanagement.demo.utils.ResponseResult;
import dailymanagement.demo.utils.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.apache.catalina.User;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Profiles;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation("修改用户信息但不可修改密码")
    @PostMapping("/updateUser")
    public ResponseResult updateUser(@RequestBody @ApiParam(value = "用户信息", required = true) Userinfo user) throws MyException {
        if (user.getUnam() == null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            TokenUser tokenUser = (TokenUser) request.getAttribute(JwtUtil.USER);
            user.setUnam(tokenUser.getUanm());
        }
        userService.updateUser(user);
        return ResponseResult.success();
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public ResponseResult updatePassword(@RequestParam @ApiParam(value = "旧密码", name = "oldpw", required = true) String oldpw,
                                         @RequestParam @ApiParam(value = "新密码", name = "newpw", required = true) String newPw) throws MyException {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        TokenUser tokenUser = (TokenUser) request.getAttribute(JwtUtil.USER);
        userService.updatePassword(tokenUser.getUanm(), oldpw, newPw);
        return ResponseResult.success();
    }

    @ApiOperation("获取所有的平台")
    @GetMapping("/getPlatforms")
    public ResponseResult getPlatforms() {
        List<String> platforms = userService.getPlatforms();
        return ResponseResult.success(platforms);
    }

    @ApiOperation("获取平台用户 如果不传平台则查询所有")
    @GetMapping("/getPlatformUser")
    public ResponseResult getPlatformUser(@RequestParam(required = false) @ApiParam(value = "平台名", required = false) String platform) {
        List<PlatFormUser> platFormUsers = userService.getPlatformUser(platform);
        return ResponseResult.success(platFormUsers);
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

    @ApiOperation("上传文件")
    @PostMapping("/uploadFiles")
    public ResponseResult uploadFiles(@RequestParam(required = true) @ApiParam(value = "文件", required = true) MultipartFile files[],
                                      @RequestParam(required = true) @ApiParam(value = "文件类型", required = true) String doctype,
                                      @RequestParam(required = true) @ApiParam(value = "提交者", required = false) String username) throws IOException {
        if (username == null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            TokenUser tokenUser = (TokenUser) request.getAttribute(JwtUtil.USER);
            username = tokenUser.getUanm();
        }
        List<Integer> ids = userService.uploadFiles(files, doctype,username);
        return ResponseResult.success(ids);
    }

    @ApiOperation(("上传图片"))
    @PostMapping("/uploadImages")
    public ResponseResult uploadImages(@RequestParam(required = true) @ApiParam(value = "图片", required = true) MultipartFile image[]) throws IOException {
        List<String> urls = userService.uploadImages(image);
        return ResponseResult.success(urls);
    }

    /**
     * 获取文件
     *
     * @param fid
     * @return 返回文件
     */
    @ApiOperation("获取文件")
    @GetMapping("/getFile/{id}")
    public ResponseEntity getFile(@PathVariable("id") @ApiParam(value = "文件id", required = true) Integer fid,
                                  HttpServletResponse response) {
        DocumentFile dfile = userService.getFile(fid);
        // 文件名
        String fileName = dfile.getFname();
        if (fileName != null) {
            //设置文件路径
            File file = new File(dfile.getFpath());
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResponseEntity.status(400).build();

    }

    @ApiOperation("上传或更新项目")
    @PostMapping("/updateProject")
    public ResponseResult updateProject(@RequestBody @ApiParam(value = "项目信息", required = true) Project project) {
        Integer id = userService.updateProject(project);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pid",id);
        return ResponseResult.success(map);
    }

    @ApiOperation(("更新项目的文件"))
    @PostMapping("updateProjectDoc")
    public ResponseResult updateProjectDoc(@RequestBody @ApiParam(value = "项目的文件",required = true)ProjectDoc projectDoc){
        userService.updateProjectDoc(projectDoc);
        return ResponseResult.success();
    }

    /**
     * 未完成
     *
     * @param username
     * @return
     */
    @ApiOperation("获取用户的所有文件")
    @GetMapping("/getUserDocs")
    public ResponseResult getUserDocs(@RequestParam @ApiParam(value = "用户名", required = false) String username) {
        if (username == null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            TokenUser tokenUser = (TokenUser) request.getAttribute(JwtUtil.USER);
            username = tokenUser.getUanm();
        }
        List<DocumentFile> bps = userService.getUserDoc(username);
        return ResponseResult.success(bps);
    }

    @ApiOperation("获取用户所有的项目")
    @GetMapping("/getUserProjects")
    public ResponseResult getUserProjects(@RequestParam @ApiParam(value = "用户名", required = false) String username){
        if (username == null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            TokenUser tokenUser = (TokenUser) request.getAttribute(JwtUtil.USER);
            username = tokenUser.getUanm();
        }
        List<BaseProject> bps = userService.getUserProjects(username);
        return ResponseResult.success();
    }

    @ApiOperation("获取项目的各种所有文件 若doctype=null 则返回所有")
    @GetMapping("/getProjectDocs")
    public ResponseResult getProjectDocs(@RequestParam @ApiParam(value = "文件类型", required = false) Integer pid,
                                         @RequestParam @ApiParam(value = "文件类型", required = false) String doctype){
       List<DocumentFile> files = userService.getProjectDocs(pid,doctype);
       return ResponseResult.success(files);
    }


}

