package dailymanagement.demo.bean;

import java.util.List;

public class BrainChatResult {
    BrainChat brainChat;
    List<String> userinfo;

    public BrainChatResult(BrainChat brainChat, List<String> userinfo) {
        this.brainChat = brainChat;
        this.userinfo = userinfo;
    }

    public BrainChat getBrainChat() {
        return brainChat;
    }

    public void setBrainChat(BrainChat brainChat) {
        this.brainChat = brainChat;
    }

    public List<String> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(List<String> userinfo) {
        this.userinfo = userinfo;
    }
}
