package dailymanagement.demo.service;

import dailymanagement.demo.bean.DocumentType;
import dailymanagement.demo.bean.PlanAndSummary;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.resultbean.PandS;
import dailymanagement.demo.exception.MyException;

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
     */
    void getUserDoc(String username);

    /**
     * 更新密码
     * @param oldpw
     * @param newpw
     * @param username
     */
    void updatePassword(String username,String oldpw,String newpw) throws MyException;
}
