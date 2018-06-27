package hello;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import javax.json.JsonObject;
import java.util.Iterator;


/**
 * Created by dlammers on 07-Nov-17.
 */
public class ApiSteps {

    private String response;
    private JSONParser jsonParser = new JSONParser();




    @When("^I call get messages$")
    public void iCallGetMessages() throws Throwable {
        String url = "http://localhost:8080/messages";
        response = ApiClient.GET(url);
    }

    @Then("^the response shows (\\d+) messages$")
    public void theResponseShowsMultipleMessages(int expectedNumberOfMessages) throws Throwable {
        JSONArray messagesArray = (JSONArray) ((JSONObject) jsonParser.parse(response)).get("messages");
        Assert.assertEquals(expectedNumberOfMessages,messagesArray.size());
    }

    @And("^every messages has an id, subject and body$")
    public void everyMessagesHasAnIdSubjectAndBody() throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);
        Iterator<JsonNode> messageIterator = node.path("messages").elements();
        while (messageIterator.hasNext()) {
            JsonNode currentNode = messageIterator.next();
            int expectedIdSize = 4;
            Assert.assertEquals(expectedIdSize,currentNode.path("id").textValue().length());
            Assert.assertFalse(currentNode.path("subject").textValue().isEmpty());
            Assert.assertFalse(currentNode.path("body").textValue().isEmpty());
        }
    }
}
