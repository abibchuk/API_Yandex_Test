import com.test.test.CommonTest;
import com.test.test.DirTest;
import com.test.test.FileTest;
import com.test.test.TrashTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.test.data.TestData.*;


public class YandexApiTest {

    @Test
    public void testTotalSpaceGetMethod() throws IOException, ParseException {

        CommonTest commonTest= new CommonTest(diskUrl);
        int responseCode = commonTest.getTotalSpace();

        Assert.assertEquals(responseCode, 200);

        JSONObject jsonObj = commonTest.parseResponse();
        String totalSpace = jsonObj.get("total_space").toString();

        Assert.assertEquals(totalSpace, space);

    }

    @Test
    public void testFileCopyPostMethod() throws IOException {

        FileTest fileTest= new FileTest(fileUrl);
        int responseCode = fileTest.fileCopyTest();

        Assert.assertEquals(responseCode, 201, 202);
    }

    @Test
    public void testDirCreatePutMethod() throws IOException {

        DirTest dirTest= new DirTest(diskUrl+"resources/?path=%2FTestDir");
        int responseCode = dirTest.createDir();
        Assert.assertEquals(responseCode, 201);
    }

    @Test
    public void testDirDeleteMethod() throws IOException {

        DirTest dirTest= new DirTest(diskUrl+"resources/?path=%2FTestDir");
        int responseCode = dirTest.deleteDir();
        Assert.assertEquals(responseCode, 204, 202);
    }

    @Test
    public void testTrashInfoGetMethod() throws IOException, ParseException {

        TrashTest trashTest = new TrashTest(diskUrl+"trash/resources/?path=%2FTestDir");
        int responseCode = trashTest.getTrashInfo();

        Assert.assertEquals(responseCode, 200);

        JSONObject jsonObj = trashTest.parseResponse();

        String name = jsonObj.get("name").toString();
        Assert.assertEquals(name, "TestDir");
        String type = jsonObj.get("type").toString();
        Assert.assertEquals(type, "dir");

    }

    @Test
    public void testTrashDelDeleteMethod() throws IOException {

        TrashTest trashTest = new TrashTest(diskUrl+"trash/resources/?");
        int responseCode = trashTest.delTrash();

        Assert.assertEquals(responseCode, 204, 202);
    }


}
