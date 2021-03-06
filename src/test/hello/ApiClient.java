package hello;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dlammers on 07-Nov-17.
 */
public class ApiClient {

    public static ResponseObject POST(String targetURL, String body, Map<String,String> headers) {

        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> singleProperty : headers.entrySet()){
                connection.setRequestProperty(singleProperty.getKey(),singleProperty.getValue());
            }

            connection.setRequestProperty("Content-Length",
                    Integer.toString(body.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(body);
            wr.close();

            //Get Response

            ResponseObject responseObject = new ResponseObject(connection);
            return responseObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static ResponseObject GET(String targetURL) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        return GET(targetURL,headers);
    }

    public static ResponseObject GET(String targetURL,Map<String,String> headers) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            for (Map.Entry<String, String> singleProperty : headers.entrySet()){
                connection.setRequestProperty(singleProperty.getKey(),singleProperty.getValue());
            }



            connection.setUseCaches(false);
            connection.setDoOutput(true);

//            //Send request
//            DataOutputStream wr = new DataOutputStream(
//                    connection.getOutputStream());
//            wr.writeBytes(body);
//            wr.close();
            ResponseObject responseObject = new ResponseObject(connection);
            return responseObject;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    public static ResponseObject DELETE(String targetURL, Map<String, String> headers) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            for (Map.Entry<String, String> singleProperty : headers.entrySet()){
                connection.setRequestProperty(singleProperty.getKey(),singleProperty.getValue());
            }



            connection.setUseCaches(false);
            connection.setDoOutput(true);

//            //Send request
//            DataOutputStream wr = new DataOutputStream(
//                    connection.getOutputStream());
//            wr.writeBytes(body);
//            wr.close();

            ResponseObject responseObject = new ResponseObject(connection);
            return responseObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}

