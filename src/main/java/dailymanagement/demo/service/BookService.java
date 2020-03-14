package dailymanagement.demo.service;

import dailymanagement.demo.bean.Book;
import dailymanagement.demo.dao.BookMapper;
import dailymanagement.demo.service.impl.BookServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceImpl {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getAll() {
        return bookMapper.getAll();
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insertSelective(record);
    }

    @Override
    public List<Book> findByName(String bname) {
        return bookMapper.selectByName(bname);
    }
}
