package dailymanagement.demo.service.impl;


import dailymanagement.demo.bean.Book;

import java.util.List;

public interface BookServiceImpl {

    List<Book> getAll();

    int insert(Book record);

    List<Book> findByName(String bname);
}
