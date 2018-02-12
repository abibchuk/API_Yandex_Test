package com.test.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.test.data.TestData.token;

public class CommonTest {

    protected URL url;
    protected HttpsURLConnection connection;

    public CommonTest(String url) {
        try {
            this.url = new URL(url);
            connection = (HttpsURLConnection) this.url.openConnection();
            connection.setRequestProperty("Authorization", "OAuth "+ token);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalSpace () throws IOException {
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();
        return responseCode;
    }

    public JSONObject parseResponse() throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response.toString());
        return (JSONObject) obj;

    }



}
