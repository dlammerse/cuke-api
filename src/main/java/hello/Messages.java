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

    //    public HashMap<String, Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(HashMap<String, Message> messages) {
//        this.messages = messages;
//    }
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Message get(String uuid) throws Exception {
        return messagesMap.get(uuid);
//        for (Message message : messages) {
//            if (message.getId().equals(uuid)) {
//                return message;
//            }
//        }
//        throw new Exception();
    }

    public void put(String uuid, Message messageToCreate) {
        messagesMap.put(uuid, messageToCreate);
        messages = new ArrayList<Message>(messagesMap.values());
//        for (Message message : messages) {
//            if (message.getId().equals(uuid)) {
//                message = messageToCreate;
//                return;
//            }
//        }
//        messages.add(messageToCreate);
    }

    public void remove(String uuid) throws Exception {
        messagesMap.remove(uuid);
        messages = new ArrayList<Message>(messagesMap.values());
//        for (Message message : messages) {
//            if (message.getId().equals(uuid)) {
//                messages.remove(message);
//                return;
//            }
//        }
//        throw  new Exception();
    }
}
