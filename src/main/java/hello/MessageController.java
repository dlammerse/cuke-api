package hello;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MessageController {

//    private static HashMap<String, Message> messages = new HashMap();
    private static Messages messages = new Messages();

    @RequestMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public Messages get() {
        return messages;
    }

    @RequestMapping("/messages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message get(@PathVariable("id") String uuid) throws Exception {
        return messages.get(uuid);
    }

    @RequestMapping(value="/messages", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Message post(@RequestBody Message message) throws Exception {
        int uuidLength = 4;
        String uuid = generateUUID(uuidLength);
        while (messages.get(uuid) != null){
            uuid = generateUUID(uuidLength);
        }
        message.setId(uuid);
        messages.put(message.getId(), message);
        return message;
    }

    @RequestMapping(value="/messages/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Message put(@PathVariable("id") String uuid, @RequestBody Message message) {
        message.setId(uuid);
        messages.put(uuid, message);
        return message;
    }
    @RequestMapping(value="/messages/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String uuid) throws Exception {
        messages.remove(uuid);
        return;
    }

    private String generateUUID(int uuidLength) {
        return UUID.randomUUID().toString().substring(0, uuidLength);
    }

}