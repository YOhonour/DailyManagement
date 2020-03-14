package dailymanagement.demo.service.impl;

import dailymanagement.demo.bean.Vip;

import java.util.List;

public interface VipServiceImpl {

    int insert(Vip record);

    List<Vip> getAll();

    List<Vip> findByName(String bnam);

}
