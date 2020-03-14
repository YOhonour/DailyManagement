package dailymanagement.demo.service;

import dailymanagement.demo.bean.BrainChat;
import dailymanagement.demo.dao.BrainChatMapper;
import dailymanagement.demo.service.impl.BrainChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrainChatService implements BrainChatServiceImpl {
    @Autowired
    BrainChatMapper brainChatMapper;
    @Override
    public int brainchatPublish(int brainid, String comment, int userid) {
        BrainChat brainChat = new BrainChat(brainid, comment, userid);
           return  brainChatMapper.insert(brainChat);

    }

    @Override
    public List<BrainChat> brainchatsBybrainid(int brainid) {
        return brainChatMapper.selectBybrainid(brainid);
    }
}
