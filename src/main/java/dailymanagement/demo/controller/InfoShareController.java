package dailymanagement.demo.controller;

import dailymanagement.demo.bean.Book;
import dailymanagement.demo.bean.DocumentFile;
import dailymanagement.demo.bean.Vip;
import dailymanagement.demo.bean.VipType;
import dailymanagement.demo.service.BookService;
import dailymanagement.demo.service.DocumentFileService;
import dailymanagement.demo.service.VipService;
import dailymanagement.demo.service.VipTypeService;
import dailymanagement.demo.utils.JsonDateValueProcessor;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.eclipse.sisu.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资料共享部分，包括三个部分：
 * 1、会议文档
 * 2、书籍借阅
 * 3、会员资源
 */

@RestController
@RequestMapping("infoshare")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InfoShareController {

    //用于提交表单时将String转换为以下设置的Date格式
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    BookService bookService;

    @Autowired
    VipService vipService;

    @Autowired
    DocumentFileService documentFileService;

    @Autowired
    VipTypeService vipTypeService;


    /**
     * 获取所有图书
     * @return 图书list表
     */
    @GetMapping("/allbook")
    public String getAllBook(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<Book> list = bookService.getAll();
        for (Book book: list){
            System.out.println(book.toString());
        }
        session.setAttribute("booklist",list);
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        jsonObject.put("data",list);
        return jsonObject.toString();
    }

    /**
     * 获取1类型(服务器)书籍
     * @param session
     * @return
     */
    @GetMapping("/book1")
    public String getBookType1(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<Book> list = bookService.getAll();
        List<Book> books = new ArrayList<>();
        for (Book book: list){
            if (book.getBtype().equals("服务器")){
                books.add(book);
                System.out.println(book.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        jsonObject.put("data",books);
        session.setAttribute("book1",books);
        return jsonObject.toString();
    }

    /**
     * 获取2类型(客户端)书籍
     * @param session
     * @return
     */
    @GetMapping("/book2")
    public String getBookType2(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<Book> list = bookService.getAll();
        List<Book> books = new ArrayList<>();
        for (Book book: list){
            if (book.getBtype().equals("客户端")){
                books.add(book);
                System.out.println(book.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        jsonObject.put("data",books);
        session.setAttribute("book1",books);
        return jsonObject.toString();
    }

    /**
     * 获取3类型书(产品)籍
     * @param session
     * @return
     */
    @GetMapping("/book3")
    public String getBookType3(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<Book> list = bookService.getAll();
        List<Book> books = new ArrayList<>();
        for (Book book: list){
            if (book.getBtype().equals("产品")){
                books.add(book);
                System.out.println(book.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        jsonObject.put("data",books);
        session.setAttribute("book1",books);
        return jsonObject.toString();
    }

    /**
     * 获取4类型(硬件)书籍
     * @param session
     * @return
     */
    @GetMapping("/book4")
    public String getBookType4(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<Book> list = bookService.getAll();
        List<Book> books = new ArrayList<>();
        for (Book book: list){
            if (book.getBtype().equals("硬件")){
                books.add(book);
                System.out.println(book.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        jsonObject.put("data",books);
        session.setAttribute("book1",books);
        return jsonObject.toString();
    }

    /**
     * 添加图书
     * @return 添加成功：success
     *         添加失败：failed
     */
    @PostMapping("/insertbook")
    public String insertBook(@RequestParam String bname, @RequestParam String introduction,
                             @RequestParam String btype,
                             @RequestParam("file") MultipartFile uploadFile,
                             HttpSession session, HttpServletRequest request)throws IOException{
        JSONObject jsonObject = new JSONObject();
        String realPath = request.getSession().getServletContext().getRealPath("/uploadImage");
        File dir = new File(realPath);
        if (!dir.isDirectory()){
            //若文件目录不存在则创建目录
            dir.mkdirs();
        }
        String filename = uploadFile.getOriginalFilename();
        //服务器端保存的文件目录
        File fileServer = new File(dir, filename);
        System.out.println("file文件的真实路径:" + fileServer.getAbsolutePath());
        //上传文件
        uploadFile.transferTo(fileServer);
        String iPath = request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort() +
                "/uploadImage/" + filename;
        //保存可供访问的网络路径并将filePath存进book的fpath中
        session.setAttribute("iPath",iPath);
        Book book = new Book();
        book.setBname(bname);
        book.setIntroduction(introduction);
        book.setIpath(iPath);
        book.setBtype(btype);
        int result = bookService.insert(book);

        jsonObject.put("code","200");
        jsonObject.put("message","success");
        System.out.println(book);
        jsonObject.put("code","200");
        jsonObject.put("data",book);
        session.setAttribute("book",book);
        return jsonObject.toString();

    }

    /**
     * 通过书名查询
     * @param bname
     * @param session
     * @return
     */
    @GetMapping("/findbook")
    public String findBookByName(@RequestParam String bname, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<Book> list = bookService.findByName(bname);
        for (Book book: list){
            System.out.println(book.toString());
        }
        jsonObject.put("code",200);
        jsonObject.put("message","success");
        jsonObject.put("data",list);
        session.setAttribute("booklist",list);
        return jsonObject.toString();
    }


    /**
     * 获取所有会员类型
     * @param session
     * @return
     */
    @GetMapping("/allviptype")
    public String getAllVipType(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        List<VipType> list = vipTypeService.getAll();
        for (VipType vipType: list){
            System.out.println(vipType);
        }
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        jsonObject.put("code","200");
        jsonObject.put("message","seccess");
        jsonObject.put("data",jsonArray);
        return jsonObject.toString();
    }


    /**
     * 获取迅雷会员
     * @param session
     * @return
     */
    @GetMapping("/vip/xunlei")
    public String getXunleiVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        for (Vip vip: list){
            System.out.println(vip.getTypeid());
            if (vip.getTypeid().equals(1)){
                vips.add(vip);
                System.out.println(vip.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }


    /**
     * 获取爱奇艺会员
     * @param session
     * @return
     */
    @GetMapping("/vip/aiqiyi")
    public String getAiqiyiVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 2){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取腾讯会员
     * @param session
     * @return
     */
    @GetMapping("/vip/tengxun")
    public String getTengxunVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 3){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取百度网盘会员
     * @param session
     * @return
     */
    @GetMapping("/vip/baiduwangpan")
    public String getBaiduVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 4){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取csdn会员
     * @param session
     * @return
     */
    @GetMapping("/vip/csdn")
    public String getCsdnVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 5){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取百度文库会员
     * @param session
     * @return
     */
    @GetMapping("/vip/baiduwenku")
    public String getBaiDuWenKuVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 6){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取寻图会员
     * @param session
     * @return
     */
    @GetMapping("/vip/xuntu")
    public String getXunTuVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 7){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取千图网会员
     * @param session
     * @return
     */
    @GetMapping("/vip/qiantuwang")
    public String getQianTuVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 8){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 获取其它会员
     * @param session
     * @return
     */
    @GetMapping("/vip/others")
    public String getOtherVip(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject() ;
        List<Vip> list = vipService.getAll();
        List<Vip> vips = new ArrayList<>();
        if(list == null){
            //获取失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //获取成功
            for (Vip vip: list) {
                if(vip.getTypeid() == 9){
                    vips.add(vip);
                    System.out.println(vip.toString());
                }

            }
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONArray jsonArray = JSONArray.fromObject(vips, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }

    /**
     * 添加会员
     * @param session
     * @return
     */
    @PostMapping("/insertvip")
    public String insertVip(Vip vip, HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        String name = vip.getVnam();
        if (name.equals("迅雷")){
            vip.setTypeid(1);
        } else if (name.equals("爱奇艺")){
            vip.setTypeid(2);
        } else if (name.equals("腾讯视频")){
            vip.setTypeid(3);
        } else if (name.equals("百度网盘")){
            vip.setTypeid(4);
        } else if (name.equals("csdn")){
            vip.setTypeid(5);
        } else if (name.equals("百度文库")){
            vip.setTypeid(6);
        } else if (name.equals("寻图")){
            vip.setTypeid(7);
        } else if (name.equals("千图网")){
            vip.setTypeid(8);
        } else {
            vip.setTypeid(9);
        }
        int result = vipService.insert(vip);
        if (result == 0){
            //添加失败
            jsonObject.put("code","400");
            jsonObject.put("message","failed");
        } else {
            //添加成功
            System.out.println(vip.toString());
            jsonObject.put("code","200");
            jsonObject.put("message","success");
        }
        JSONObject jsonObject1 = jsonObject.fromObject(vip,jsonConfig);
        jsonObject.put("data",jsonObject1);
        session.setAttribute("vip",vip);
        return jsonObject.toString();
    }

    /**
     * 通过名称查询会员
     * @param vnam
     * @param session
     * @return
     */
    @GetMapping("/findvip")
    public String findVipByName(@RequestParam String vnam, HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        List<Vip> list = vipService.findByName(vnam);
        for (Vip vip: list){
            System.out.println(vip.toString());
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("viplist",list);
        return jsonObject.toString();
    }


    /**
     * 获取全部文档文件
     * @param session
     * @return
     */
    @GetMapping("/alldoc")
    public String getAllDoc(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        List<DocumentFile> list = documentFileService.getAll();
        for (DocumentFile documentFile: list){
            System.out.println(documentFile.toString());
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("doclist",list);
        return jsonObject.toString();
    }

    /**
     * 获取1类型(项目汇报)文档
     * @param session
     * @return
     */
    @GetMapping("/doc1")
    public String getDocType1(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        List<DocumentFile> list = documentFileService.getAll();
        List<DocumentFile> documentFiles = new ArrayList<>();
        for (DocumentFile documentFile: list){
            if (documentFile.getFtype().equals("项目汇报")){
                documentFiles.add(documentFile);
                System.out.println(documentFile.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(documentFiles,jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("doc1",documentFiles);
        return jsonObject.toString();
    }

    /**
     * 获取2类型(技术交流)文档
     * @param session
     * @return
     */
    @GetMapping("/doc2")
    public String getDocType2(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        List<DocumentFile> list = documentFileService.getAll();
        List<DocumentFile> documentFiles = new ArrayList<>();
        for (DocumentFile documentFile: list){
            if (documentFile.getFtype().equals("技术交流")){
                documentFiles.add(documentFile);
                System.out.println(documentFile.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(documentFiles,jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("doc2",documentFiles);
        return jsonObject.toString();
    }

    /**
     * 获取3类型(会议纪要)文档
     * @param session
     * @return
     */
    @GetMapping("/doc3")
    public String getDocType3(HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        List<DocumentFile> list = documentFileService.getAll();
        List<DocumentFile> documentFiles = new ArrayList<>();
        for (DocumentFile documentFile: list){
            if (documentFile.getFtype().equals("会议纪要")){
                documentFiles.add(documentFile);
                System.out.println(documentFile.toString());
            }
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(documentFiles,jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("doc3",documentFiles);
        return jsonObject.toString();
    }


    /**
     * 插入文档
     * @param documentFile
     * @param uploadFile
     * @param session
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/insertdoc")
    public String insertDoc(DocumentFile documentFile,
                            @RequestParam("file") MultipartFile uploadFile,
                            HttpSession session, HttpServletRequest request) throws IOException{
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile");
        File dir = new File(realPath);
        if (!dir.isDirectory()){
            //若文件目录不存在则创建目录
            dir.mkdirs();
        }
        String filename = uploadFile.getOriginalFilename();
        //服务器端保存的文件目录
        File fileServer = new File(dir, filename);
        System.out.println("file文件的真实路径:" + fileServer.getAbsolutePath());
        //上传文件
        uploadFile.transferTo(fileServer);
        String fPath = request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort() +
                "/uploadFile/" + filename;
        //保存可供访问的网络路径并将filePath存进book的fpath中
        documentFile.setFpath(fPath);
        System.out.println(documentFile);
        int result = documentFileService.insert(documentFile);
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONObject jsonObject1 = JSONObject.fromObject(documentFile,jsonConfig);
        jsonObject.put("data",jsonObject1);
        session.setAttribute("doc",documentFile);
        return jsonObject.toString();
    }


    /**
     * 查询文档
     * @param session
     * @return
     */
    @GetMapping("/finddoc")
    public String findByTimeorName(@RequestParam @Nullable String fname, @RequestParam @Nullable Date time,
                                   HttpSession session){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObject = new JSONObject();
        System.out.println(time);
        List<DocumentFile> list = documentFileService.findByTimeorName(fname,time);
        System.out.println(list.isEmpty());
        for(DocumentFile documentFile: list){
            System.out.println(documentFile.toString());
        }
        jsonObject.put("code","200");
        jsonObject.put("message","success");
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        jsonObject.put("data",jsonArray);
        session.setAttribute("doc",list);
        return jsonObject.toString();
    }
}

