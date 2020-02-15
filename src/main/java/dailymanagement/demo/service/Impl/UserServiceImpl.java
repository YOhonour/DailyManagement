package dailymanagement.demo.service.Impl;

import dailymanagement.demo.bean.*;
import dailymanagement.demo.bean.resultbean.BaseProject;
import dailymanagement.demo.bean.resultbean.PandS;
import dailymanagement.demo.bean.vo.PlatFormUser;
import dailymanagement.demo.bean.vo.ProjectDoc;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.mapper.*;
import dailymanagement.demo.service.UserService;
import dailymanagement.demo.utils.CosUtil;
import dailymanagement.demo.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/8 11:40
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private PlanAndSummaryMapper planAndSummaryMapper;

    @Autowired
    private DocumentTypeMapper documentTypeMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private DocumentFileMapper documentFileMapper;

    @Autowired
    private CosUtil cosUtil;

    @Value("${config.file.baseFile_location}")
    private String base_file_location;

    @Value("${config.file.baseFile_url}")
    private String base_file_url;

    @Override
    public Userinfo login(String username, String password) {
        return userinfoMapper.login(username, password);
    }

    @Override
    public Userinfo getUserInfoByUnam(String username) {
        return userinfoMapper.selectByPrimaryKey(username);
    }

    @Override
    public void updateUser(Userinfo user) throws MyException {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(user.getUnam());
        if (userinfo == null) {
//            userinfoMapper.insertSelective(user);
            throw new MyException(Status.NotFound);
        } else {
            userinfoMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public void updatePaS(PlanAndSummary pas) {
        if (pas.getId() != null) {
            pas.setWriteTime(new Date());
            planAndSummaryMapper.updateByPrimaryKey(pas);
        } else {
            pas.setUpdateTime(new Date());
            planAndSummaryMapper.insert(pas);
        }
    }

    @Override
    public List<PandS> getUserPaSs(String username) {
        return planAndSummaryMapper.getUserPaSs(username);
    }

    @Override
    public PlanAndSummary getDetailPaS(Integer id, String username) {
        return planAndSummaryMapper.selectByPrimaryKey(id, username);
    }

    @Override
    public List<DocumentType> getDocTypes() {
        return documentTypeMapper.selectAll();
    }

    @Override
    public List<DocumentFile> getUserDoc(String username) {
       List<DocumentFile> files = documentFileMapper.getUserDocs(username);
        return files;
    }

    @Override
    public void updatePassword(String username, String oldpw, String newpw) throws MyException {
        Userinfo userinfo = userinfoMapper.login(username, oldpw);
        if (userinfo == null) {
            throw new MyException(Status.FAULT_PASSWORD);
        } else {
            userinfoMapper.updatePassword(username, newpw);
        }
    }

    @Override
    public List<Integer> uploadFiles(MultipartFile[] files, String doctype,String uname) throws IOException {
        int length = files.length;
        List<Integer> ids=new ArrayList<>();
        //插入类型
        DocumentType type = new DocumentType();
        type.setDoctype(doctype);
        DocumentType type1 = documentTypeMapper.select(type);
        if(type1 == null){
            documentTypeMapper.insert(type);
        }
        //保存文件
        for (int i = 0; i < length; i++) {
            Integer id = saveFile(files[i], doctype,uname);
            ids.add(id);
        }
        return ids;
    }

    @Override
    public DocumentFile getFile(Integer fid) {
        DocumentFile file = userinfoMapper.getFileById(fid);
        return file;
    }

    @Override
    public Integer updateProject(Project project) {
        if (project.getPid() != null) {
            projectMapper.updateByPrimaryKeySelective(project);
        } else {
            projectMapper.insertSelective(project);
        }
        //插入成员
        projectMapper.insertMembers(project);
        return project.getPid();
    }

    @Override
    public List<String> uploadImages(MultipartFile[] image) throws IOException {
        int length = image.length;
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String url = cosUtil.upload(image[i]);
            urls.add(url);
        }
        return urls;
    }

    @Override
    public List<String> getPlatforms() {
        return userinfoMapper.getPlatforms();
    }

    @Override
    public List<PlatFormUser> getPlatformUser(String platform) {
        return userinfoMapper.getPlatformUser(platform);
    }

    @Override
    public void updateProjectDoc(ProjectDoc projectDoc) {
        projectMapper.updateProjectDoc(projectDoc);
    }

    @Override
    public List<BaseProject> getUserProjects(String username) {
        return projectMapper.getUserProjects(username);
    }

    @Override
    public List<DocumentFile> getProjectDocs(Integer pid, String doctype) {
        return documentFileMapper.getProjectDocs(pid,doctype);
    }

    private Integer saveFile(MultipartFile file, String doctype,String uname) throws IOException {

        String fname = file.getOriginalFilename();
        Date time = new Date();
        String fpath = base_file_location + time.getTime() + '_' + fname;
        System.out.println(fpath);
        file.transferTo(new File(fpath));
        //保存到数据库
        DocumentFile docfile = new DocumentFile();
        docfile.setDoctype(doctype);
        docfile.setFpath(fpath);
        docfile.setFname(fname);
        docfile.setTime(time);
        docfile.setdUnam(uname);
        userinfoMapper.savefile(docfile);
        //访问地址
        Integer fid = docfile.getFid();
        return fid;

    }


}
