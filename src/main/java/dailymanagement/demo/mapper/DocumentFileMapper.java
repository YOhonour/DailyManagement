package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.DocumentFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Mapper
public interface DocumentFileMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(DocumentFile record);

    int insertSelective(DocumentFile record);

    DocumentFile selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(DocumentFile record);

    int updateByPrimaryKey(DocumentFile record);

    /**
     * 获取项目文件
     * @param pid  项目
     * @param doctype 文件类型
     * @return
     */
    List<DocumentFile> getProjectDocs(Integer pid, String doctype);

    /**
     * 获取用户所有文档
     * @param username 用户名
     * @return 文件
     */
    public List<DocumentFile> getUserDocs(String username);

}