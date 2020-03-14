package dailymanagement.demo.dao;

import dailymanagement.demo.bean.DocumentType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocumentTypeMapper {
    int deleteByPrimaryKey(String doctype);

    int insert(DocumentType record);

    int insertSelective(DocumentType record);
}