package dailymanagement.demo.service;

import dailymanagement.demo.bean.Brainstorm;
import dailymanagement.demo.dao.BrainstormMapper;
import dailymanagement.demo.service.impl.BrainstormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrainstormService implements BrainstormServiceImpl {
    @Autowired
    BrainstormMapper brainstormMapper;
    @Override
    public int publish(String title, int userid) {
        Brainstorm brainstorm = new Brainstorm(title);
        return   brainstormMapper.insert(brainstorm);
    }

    @Override
    public List<Brainstorm> getall() {
        return brainstormMapper.getall();
    }



}
