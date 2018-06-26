package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DLAMMERS on 26-Jun-18.
 */
public class Messages {


    private List<Message> messages = new ArrayList<>();
    private HashMap<String, Message> messagesMap = new HashMap();

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Message get(String uuid) throws Exception {
        return messagesMap.get(uuid);
    }

    public void put(String uuid, Message messageToCreate) {
        messagesMap.put(uuid, messageToCreate);
        messages = new ArrayList<Message>(messagesMap.values());
    }

    public void remove(String uuid) throws Exception {
        messagesMap.remove(uuid);
        messages = new ArrayList<Message>(messagesMap.values());
    }
}
