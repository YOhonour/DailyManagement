package dailymanagement.demo.dao;

import dailymanagement.demo.bean.DocumentFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eclipse.sisu.Nullable;

import java.util.Date;
import java.util.List;

@Mapper
public interface DocumentFileMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(DocumentFile record);

    int insertSelective(DocumentFile record);

    DocumentFile selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(DocumentFile record);

    int updateByPrimaryKey(DocumentFile record);

    List<DocumentFile> getAll();

    List<DocumentFile> selectByTimeorName(@Param("fname") String fname,
                                          @Param("ftime") Date time);



}