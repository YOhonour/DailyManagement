package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.DocumentFile;
import dailymanagement.demo.bean.Project;
import dailymanagement.demo.bean.Userinfo;

import dailymanagement.demo.bean.vo.PlatFormUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Component
@Mapper
public interface UserinfoMapper {
    int deleteByPrimaryKey(String unam);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(String unam);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    /**
     * 登陆
     * @return 完整用户信息
     */
    Userinfo login(String unam,String password);

    /**
     * 修改用户密码
     * @param username
     * @param password
     */
    void updatePassword(String username, String password);

    /**
     * 保存文件信息到数据库
     * @param docfile 文件
     */
    void savefile(DocumentFile docfile);

    /**
     * 通过文件id获取文件
     * @param fid 文件id
     */
    DocumentFile getFileById(Integer fid);


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
    List<PlatFormUser> getPlatformUser(@Nullable @Param(value = "platform") String platform);


    Userinfo selectByUserName(String name);

    Userinfo selectByuid(int id);


    void insertUser(String unam);
}