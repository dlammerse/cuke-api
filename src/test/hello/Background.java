package hello; /**
 * Created by dlammers on 07-Nov-17.
 */

import cucumber.api.java.en.Given;

import java.util.HashMap;
import java.util.Map;


public class Background {
    @Given("^I created (\\d+) standard messages$")
    public void iCreatedStandardMessages(int numberOfMessages) throws Throwable {
        String url = "http://localhost:8080/messages";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        for (int i = 1; i <= numberOfMessages; i++) {
            String body = "{\"subject\": \"onderwerp" + i + "\",\"body\": \"body" + i + "\"}";
            ApiClient.POST(url, body, headers);
        }
    }
}
