package dailymanagement.demo.service.impl;

import dailymanagement.demo.bean.DocumentFile;

import java.util.Date;
import java.util.List;

public interface DocumentFileServiceImpl {
    List<DocumentFile> getAll();

    int insert(DocumentFile record);

    List<DocumentFile> findByTimeorName(String fname, Date time);

}
