package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by DLAMMERS on 14-Jan-19.
 */
public class ResponseObject {
    private final HttpURLConnection urlConntection;


    public ResponseObject(HttpURLConnection connection) {
        this.urlConntection = connection;
    }

//    public InputStream getInputStream() throws IOException {
//        return urlConntection.getInputStream();
//    }

    public String response() throws IOException {
        InputStream is = urlConntection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

    public int statusCode() throws IOException {
        return urlConntection.getResponseCode();
    }
}
