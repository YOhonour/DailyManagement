package dailymanagement.demo.service;

import dailymanagement.demo.bean.Vip;
import dailymanagement.demo.dao.VipMapper;
import dailymanagement.demo.service.impl.VipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipService implements VipServiceImpl {

    @Autowired
    VipMapper vipMapper;

    @Override
    public int insert(Vip record) {
        return vipMapper.insertSelective(record);
    }

    @Override
    public List<Vip> getAll() {
        return vipMapper.getAll();
    }

    @Override
    public List<Vip> findByName(String bnam) {
        return vipMapper.selectByName(bnam);
    }
}
