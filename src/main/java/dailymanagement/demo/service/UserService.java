package dailymanagement.demo.service;

import dailymanagement.demo.bean.*;
import dailymanagement.demo.bean.resultbean.BaseProject;
import dailymanagement.demo.bean.resultbean.PandS;
import dailymanagement.demo.bean.vo.PlatFormUser;
import dailymanagement.demo.bean.vo.ProjectDoc;
import dailymanagement.demo.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/8 11:40
 */

public interface UserService {
    /**
     * 登陆
     * @return 完整的用户信息
     */
    public Userinfo login(String username , String password);

    /**
     * 通过用户名 获取用户详细信息
     * @param username  用户名
     * @return
     */
    Userinfo getUserInfoByUnam(String username);

    /**
     * 修改用户信息 除开密码
     * @param user
     */
    void updateUser(Userinfo user) throws MyException;

    /**
     * 添加或更新工作计划与总结
     * @param pas
     */
    void updatePaS(PlanAndSummary pas);

    /**
     * 通过用户名获取用户所有的工作计划与总结
     * @param username
     * @return
     */
    List<PandS> getUserPaSs(String username);

    /**
     * 通过id获取工作计划与总结的详细信息
     * @param id
     * @return
     */
    PlanAndSummary getDetailPaS(Integer id,String username);

    /**
     * 获取所有文件类型
     * @return
     */
    List<DocumentType> getDocTypes();

    /**
     * 获取用户的所有文档
     * @param username
     * @return
     */
    List<DocumentFile> getUserDoc(String username);

    /**
     * 更新密码
     * @param oldpw
     * @param newpw
     * @param username
     */
    void updatePassword(String username,String oldpw,String newpw) throws MyException;

    /**
     * 上传图片
     * @param files 图片
     * @param doctype 文件类型
     * @return 图片的url
     */
    List<Integer> uploadFiles(MultipartFile[] files,String doctype,String uname) throws IOException;


    /**
     * 获取文件信息
     * @param fid 文件id
     * @return 文件的实际地址
     */
    DocumentFile getFile(Integer fid);

    /**
     * 上传或更新项目
     * @param project  项目信息
     */
    Integer updateProject(Project project);

    /**
     * 上传图片
     * @param image
     * @return
     */
    List<String> uploadImages(MultipartFile[] image) throws IOException;

    /**
     * 获取所有的平台
     * @return
     */
    List<String> getPlatforms();

    /**
     * 查询平台用户
     * @param platform
     * @return
     */
    List<PlatFormUser> getPlatformUser(String platform);

    /**
     * 让文件与项目关联
     * @param projectDoc
     */
    void updateProjectDoc(ProjectDoc projectDoc);

    /**
     * 获取用户的所有项目
     * @param username
     * @return
     */
    List<BaseProject> getUserProjects(String username);

    /**
     * 通过文件类型获取项目的所有文件
     * @param doctype
     * @param pid
     * @return
     */
    List<DocumentFile> getProjectDocs(Integer pid,String doctype);
}
