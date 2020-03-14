package dailymanagement.demo.service.impl;

import dailymanagement.demo.bean.BrainChat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrainChatServiceImpl {
    int brainchatPublish(int brainid, String comment, int userid);

    List<BrainChat> brainchatsBybrainid(int brainid);
}
