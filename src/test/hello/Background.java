package hello; /**
 * Created by dlammers on 07-Nov-17.
 */

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


public class Background {
    @Given("^make sure (\\d+) standard (?:messages|message) is available$")
    public void iCreatedStandardMessages(int numberOfMessages) throws Throwable {
        String url = "http://localhost:8080/messages";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        String getResponse = ApiClient.GET(url);
        // TODO: read out get response to see how much need to be added
        for (int i = 1; i <= numberOfMessages; i++) {
            String body = "{\"subject\": \"onderwerp" + i + "\",\"body\": \"body" + i + "\"}";
            ApiClient.POST(url, body, headers);
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
        Process apiProcess = Runtime.getRuntime().exec("java -jar target/gs-spring-boot-0.1.0.jar");
        BufferedReader inputApiProcess = new BufferedReader(new InputStreamReader(apiProcess.getInputStream()));
        String outputApiProcess = null;
        while((outputApiProcess= inputApiProcess.readLine()) != null){
            if (outputApiProcess.contains("Started Application in")){
                System.out.println("API is started");
                return;
            }
        }

    }
}
