package hello;

import cucumber.api.PendingException;
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

import javax.json.JsonObject;


/**
 * Created by dlammers on 07-Nov-17.
 */
public class ApiSteps {

    private String response;




    @When("^I call get messages$")
    public void iCallGetMessages() throws Throwable {
        String url = "http://localhost:8080/messages";
        response = ApiClient.GET(url);
        JSONParser jsonParser = new JSONParser();
        JSONArray jarray = (JSONArray) jsonParser.parse(response);
    }

    @Then("^the response shows multiple messages$")
    public void theResponseShowsMultipleMessages() throws Throwable {
        System.out.println("response = " + response);
//        JsonObject jsonObj = new JsonObject (response);
        System.out.println("response = " + response.toString());
        System.out.println("lengte = "  + response.length());
        System.out.println("1234c wfsadf asd f");
    }
}
