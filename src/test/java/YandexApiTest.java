import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by Anjey on 07.02.2018.
 */
public class YandexApiTest {

    public static final String diskUrl = "https://cloud-api.yandex.net/v1/disk/";
    public static final String fileUrl = "https://cloud-api.yandex.net/v1/disk/resources/copy?from=%2Ffile.txt&path=%2Fpicture%2Ffile.txt&overwrite=true";
    public static final String token = "AQAAAAAjfKgGAATMex-MNXgynUavlf5jdCyL0JY";


    @Test
    public void testTotalSpaceGetMethod() throws IOException, ParseException {

        URL url = new URL(diskUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "OAuth "+ token);
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(responseCode, 200);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response.toString());
        JSONObject jsonObj = (JSONObject) obj;
        String totalSpace = jsonObj.get("total_space").toString();
        Assert.assertEquals(totalSpace, "10737418240");

    }

    @Test
    public void testFileCopyPostMethod() throws IOException {
        URL url = new URL(fileUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "OAuth "+token);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(responseCode, 201, 202);
    }

    @Test
    public void testDirCreatePutMethod() throws IOException {
        URL url = new URL(diskUrl+"resources/?path=%2FTestDir");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "OAuth "+token);
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(responseCode, 201);
    }

    @Test
    public void testDirDelDeleteMethod() throws IOException {
        URL url = new URL(diskUrl+"resources/?path=%2FTestDir");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "OAuth "+token);
        connection.setDoOutput(true);
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(responseCode, 204, 202);
    }

    @Test
    public void testTrashInfoGetMethod() throws IOException, ParseException {

        URL url = new URL(diskUrl+"trash/resources/?path=%2FTestDir");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "OAuth "+ token);
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(responseCode, 200);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response.toString());
        JSONObject jsonObj = (JSONObject) obj;
        String name = jsonObj.get("name").toString();
        Assert.assertEquals(name, "TestDir");
        String type = jsonObj.get("type").toString();
        Assert.assertEquals(type, "dir");

    }

    @Test
    public void testTrashDelDeleteMethod() throws IOException {
        URL url = new URL(diskUrl+"trash/resources/?");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "OAuth "+token);
        connection.setDoOutput(true);
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(responseCode, 204, 202);
    }

//    public static void main(String[] args) throws IOException, InterruptedException {

//        String query = String.format("response_type=%s&client_id=%s",
//                URLEncoder.encode("code", "UTF-8"),
//                URLEncoder.encode("9790c053f7d644438ded18a683d1f086", "UTF-8"));
//
//        URL url = new URL(authUrl + "?" + query);
//        System.out.println(url);

//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(testUrl);
//
//        request.setHeader("Authorization", "OAuth "+token);
//        HttpResponse httpResponse = client.execute(request);
//        System.out.println(httpResponse.getStatusLine().getStatusCode());
//        System.out.println(httpResponse.getEntity().getContent().toString());
//
//        if(httpResponse.getStatusLine().getStatusCode() == 200) {
//            InputStream in = httpResponse.getEntity().getContent();
//            byte b[] = new byte[1024];
//            StringBuilder html = new StringBuilder("");
//            while (in.read(b) != -1) {
//                html.append((new String(b)).toString());
//                b = new byte[1024];
//            }
//            System.out.println(html);
//        }


//        HttpContext context = new BasicHttpContext();
//        HttpResponse response = client.execute(request, context);
//        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
//            throw new IOException(response.getStatusLine().toString());
//        HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(
//                ExecutionContext.HTTP_REQUEST);
//        HttpHost currentHost = (HttpHost)  context.getAttribute(
//                ExecutionContext.HTTP_TARGET_HOST);
//        String currentUrl = (currentReq.getURI().isAbsolute()) ? currentReq.getURI().toString() : (currentHost.toURI() + currentReq.getURI());
//        System.out.println(currentUrl);

//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//        System.out.println(result);



//        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//        connection.setDoOutput(true);
//        connection.setRequestProperty("Content-Type", "text/plain");
//        connection.setRequestProperty("charset", "utf-8");
//        connection.set
//        connection.connect();
//        connection.getResponseCode();
//        URL response = connection.getURL();
//        System.out.println(response.toString());
//        connection.disconnect();


//        InputStream response = connection.getInputStream();
//        System.out.println(response);
//        try (Scanner scanner = new Scanner(response)) {
//            String responseBody = scanner.useDelimiter("verification-code-code").next();
//            System.out.println(responseBody);
//        }
//
//    }

}
