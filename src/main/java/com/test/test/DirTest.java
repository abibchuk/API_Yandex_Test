package com.test.test;

import java.io.IOException;

public class DirTest extends CommonTest {


    public DirTest(String url) {
        super(url);
    }

    public int createDir() throws IOException {

        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        int responseCode = connection.getResponseCode();

        return responseCode;
    }

    public int deleteDir() throws IOException {

        connection.setDoOutput(true);
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();

        return responseCode;
    }
}
