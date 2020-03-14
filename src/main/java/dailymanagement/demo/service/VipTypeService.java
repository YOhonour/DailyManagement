package dailymanagement.demo.service;

import dailymanagement.demo.bean.VipType;
import dailymanagement.demo.dao.VipTypeMapper;
import dailymanagement.demo.service.impl.VipTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipTypeService implements VipTypeServiceImpl {

    @Autowired
    VipTypeMapper vipTypeMapper;

    @Override
    public List<VipType> getAll() {
        return vipTypeMapper.getAll();
    }
}
