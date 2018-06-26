package hello;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
public class MessageController {

    private static HashMap<String, Message> messageList = new HashMap();

    @RequestMapping("/messages")
    public Collection<Message> get() {
        return messageList.values();
    }

    @RequestMapping("/messages/{id}")
    public Message get(@PathVariable("id") String uuid) {
        return messageList.get(uuid);
    }

    @RequestMapping(value="/messages", method = RequestMethod.POST)
    public Message post(@RequestBody Message message) {
        int uuidLength = 4;
        String uuid = generateUUID(uuidLength);
        while (messageList.get(uuid) != null){
            uuid = generateUUID(uuidLength);
        }
        message.setId(uuid);
        messageList.put(message.getId(), message);
        return message;
    }

    @RequestMapping(value="/messages/{id}", method = RequestMethod.PUT)
    public Message put(@PathVariable("id") String uuid, @RequestBody Message message) {
        message.setId(uuid);
        messageList.put(uuid, message);
        return message;
    }
    @RequestMapping(value="/messages/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String uuid) {
        messageList.remove(uuid);
        return;
    }

    private String generateUUID(int uuidLength) {
        return UUID.randomUUID().toString().substring(0, uuidLength);
    }

}