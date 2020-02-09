package dailymanagement.demo.service.Impl;

import dailymanagement.demo.bean.DocumentType;
import dailymanagement.demo.bean.PlanAndSummary;
import dailymanagement.demo.bean.Userinfo;
import dailymanagement.demo.bean.resultbean.PandS;
import dailymanagement.demo.exception.MyException;
import dailymanagement.demo.mapper.DocumentTypeMapper;
import dailymanagement.demo.mapper.PlanAndSummaryMapper;
import dailymanagement.demo.mapper.UserinfoMapper;
import dailymanagement.demo.service.UserService;
import dailymanagement.demo.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.SSLEngineResult;
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

    @Override
    public Userinfo login(String username, String password) {
        return userinfoMapper.login(username,password);
    }

    @Override
    public Userinfo getUserInfoByUnam(String username) {
        return userinfoMapper.selectByPrimaryKey(username);
    }

    @Override
    public void updateUser(Userinfo user) throws MyException {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(user.getUnam());
        if (userinfo == null){
           // userinfoMapper.insertSelective(user);
            throw new MyException(Status.NOT_LOGIN);
        }else {
            userinfoMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public void updatePaS(PlanAndSummary pas) {
        if (pas.getId()!=null){
            pas.setWriteTime(new Date());
            planAndSummaryMapper.updateByPrimaryKey(pas);
        }else {
            pas.setUpdateTime(new Date());
            planAndSummaryMapper.insert(pas);
        }
    }

    @Override
    public List<PandS> getUserPaSs(String username) {
        return planAndSummaryMapper.getUserPaSs(username);
    }

    @Override
    public PlanAndSummary getDetailPaS(Integer id,String username) {
        return planAndSummaryMapper.selectByPrimaryKey(id,username);
    }

    @Override
    public List<DocumentType> getDocTypes() {
        return documentTypeMapper.selectAll();
    }

    @Override
    public void getUserDoc(String username) {

    }

    @Override
    public void updatePassword( String username, String oldpw, String newpw) throws MyException {
        Userinfo userinfo = userinfoMapper.login(username, oldpw);
        if (userinfo == null){
            throw new MyException(Status.FAULT_PASSWORD);
        }else {
            userinfoMapper.updatePassword(username,newpw);
        }
    }


}
