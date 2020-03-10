package dailymanagement.demo.mapper;

import dailymanagement.demo.bean.DocumentType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Component
@Mapper
public interface DocumentTypeMapper {
    int deleteByPrimaryKey(String doctype);

    int insert(DocumentType record);

    int insertSelective(DocumentType record);

    /**
     * 获取所有文件类型
     * @return
     */
    List<DocumentType> selectAll();

    /**
     * 查询
     * @param type
     */
    DocumentType select(DocumentType type);
}