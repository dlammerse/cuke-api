package hello; /**
 * Created by dlammers on 07-Nov-17.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Background {
    @Given("^make sure (\\d+) standard (?:messages|message) is available$")
    public void iCreatedStandardMessages(int requiredNumberOfMessages) throws Throwable {
        String url = "http://localhost:8080/messages";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String getResponse = ApiClient.GET(url).response();
        JsonNode node = mapper.readTree(getResponse);
        JsonNode messagesNode = node.path("messages");
        Iterator<JsonNode> messageIterator = messagesNode.elements();
        int currentNumberOfMessages = messagesNode.size();
        if (currentNumberOfMessages < requiredNumberOfMessages){
            for (int i = 1; i <= requiredNumberOfMessages-currentNumberOfMessages; i++) {
                String body = "{\"subject\": \"onderwerp" + i + "\",\"body\": \"body" + i + "\"}";
                ApiClient.POST(url, body, headers);
            }
        }
        else if(currentNumberOfMessages > requiredNumberOfMessages){
            for (int i = 1; i <= currentNumberOfMessages -requiredNumberOfMessages; i++) {
                String idToDelete = messagesNode.path(i).path("id").asText();
                ApiClient.DELETE(url + "/" + idToDelete, headers);
            }
        }
        else{
            //right number of messages are there
        }
    }

    @Given("^the API is running$")
    public void theAPIIsRunning() throws Throwable {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome.substring(0,javaHome.length()-3) + "bin";
        Process jpsProcess =Runtime.getRuntime().exec(javaBin + "/jps");
        BufferedReader input = new BufferedReader(new InputStreamReader(jpsProcess.getInputStream()));
        String output = null;
        while((output= input.readLine()) != null){
            if (output.contains("gs-spring-boot-0.1.0.jar")){
                System.out.println("API is already running");
                return;
            }
        }
        String fileToExecute = getFileToExecute();


        Process apiProcess = Runtime.getRuntime().exec("java -jar " + fileToExecute);
        BufferedReader inputApiProcess = new BufferedReader(new InputStreamReader(apiProcess.getInputStream()));
        String outputApiProcess = null;
        while((outputApiProcess= inputApiProcess.readLine()) != null){
            if (outputApiProcess.contains("Started Application in")){
                System.out.println("API is started");
                return;
            }
            else if (outputApiProcess.contains("APPLICATION FAILED TO START")){
                System.out.println("API failed to start");
//                Assert.fail();
                return;
            }
        }

    }

    private String getFileToExecute() throws FileNotFoundException {

        String targetFileString = "target/gs-spring-boot-0.1.1.jar";
        String fixedFileString = "resources/gs-spring-boot-0.1.0.jar";
        File targetFile = new File(targetFileString);
        File fixedFile = new File(fixedFileString);
        String fileToExecute;
        if(targetFile.exists() && !targetFile.isDirectory()) {
             fileToExecute= targetFileString;
        }
        else if (fixedFile.exists() && !fixedFile.isDirectory()) {
            fileToExecute= fixedFileString;
        }
        else{
            throw new FileNotFoundException();
        }
        return fileToExecute;
    }
}
