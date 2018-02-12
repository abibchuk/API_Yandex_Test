package com.test.test;

import java.io.IOException;

public class TrashTest extends CommonTest{


    public TrashTest(String url) {
        super(url);
    }

    public int getTrashInfo() throws IOException {

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();

        return responseCode;
    }

    public int delTrash() throws IOException {

        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();

        return responseCode;
    }

}
