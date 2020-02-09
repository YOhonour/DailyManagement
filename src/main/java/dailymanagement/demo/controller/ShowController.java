package dailymanagement.demo.controller;

import dailymanagement.demo.bean.Book;
import dailymanagement.demo.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 成果展示部分
 */

public class ShowController {
//    @Autowired
//    BookMapper bookDao;
//
//    @Autowired
//    Book book1;
//
//    @ResponseBody
//    @RequestMapping("hello")
//    public String say(){
//        return "hello";
//    }
//
//    @RequestMapping("find/{bid}")
//    public Book findById(@PathVariable("bid") Integer bid){
//        Book book = bookDao.selectByPrimaryKey(bid);
//        System.out.println(book);
//        return book;
//    }
//
//    @RequestMapping("insert")
//    public Book insertBook(){
//        book1.setBname("白夜行");
//        book1.setEdition("东野圭吾");
//        book1.setIntroduction("神作");
//        book1.setStatus("1");
//
//        System.out.println(book1.toString());
//        bookDao.insertSelective(book1);
//        return book1;
//    }
}
