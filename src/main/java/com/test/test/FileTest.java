package com.test.test;

import java.io.IOException;

public class FileTest extends CommonTest {


    public FileTest(String url) {
        super(url);
    }

    public int fileCopyTest() throws IOException {
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();

        return responseCode;
    }


}
