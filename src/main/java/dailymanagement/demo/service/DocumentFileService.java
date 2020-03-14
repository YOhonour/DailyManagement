package dailymanagement.demo.service;

import dailymanagement.demo.bean.DocumentFile;
import dailymanagement.demo.dao.DocumentFileMapper;
import dailymanagement.demo.service.impl.DocumentFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocumentFileService implements DocumentFileServiceImpl {

    @Autowired
    DocumentFileMapper documentFileMapper;

    @Override
    public List<DocumentFile> getAll() {
        return  documentFileMapper.getAll();
    }

    @Override
    public int insert(DocumentFile record) {
        return documentFileMapper.insertSelective(record);
    }

    @Override
    public List<DocumentFile> findByTimeorName(String fname, Date time) {
        return documentFileMapper.selectByTimeorName(fname, time);
    }
}
