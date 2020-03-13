package dailymanagement.demo.service.impl;

import dailymanagement.demo.bean.Brainstorm;

import java.util.List;

public interface BrainstormServiceImpl {
    int publish(String brainid, int userid);

    List<Brainstorm> getall();
}
