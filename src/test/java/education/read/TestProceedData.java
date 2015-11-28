package education.read;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class TestProceedData {

    private ProceedData proceedData;
    private String fileTxt = "D:\\MyProb\\WebQueryingTxt\\src\\test\\resources\\testMyFile.txt";

    @Before
    public void testCheck() {
        proceedData = new ProceedData();
    }

    @Test
    public void testFind() {
        String name = "Vetal";
        Integer limit = 5;
        Integer length = 3;
        ArrayList<String> testResult = proceedData.find(fileTxt, name, limit, length);
        Assert.assertEquals("Vet, ", testResult.get(0));
    }

    @Test
    public void testFindPart() {
        String name = "";
        Integer limit = 17;
        Integer length = null;
        ArrayList<String> testResult = proceedData.find(fileTxt, name, limit, length);
        Assert.assertEquals("Vetal Vetal Vetal", testResult.get(0));
    }

    @Test
    public void testFindAll() {
        String name = "Vetal";
        Integer limit = 100;
        Integer length = 3;
        ArrayList<String> testResult = proceedData.find(fileTxt, name, limit, length);
        Assert.assertEquals("Vet, Vet, Vet, Vet, Vet, Vet, ", testResult.get(0));
    }

    @Test
    public void testMetaDataSize() throws IOException {

        ArrayList<String> testResult = proceedData.metaData(fileTxt);

        Assert.assertEquals("Size: 36 bit", testResult.get(0));
    }

    @Test
    public void testMetaDataName() throws IOException {

        ArrayList<String> testResult = proceedData.metaData(fileTxt);

        Assert.assertEquals("File name: testMyFile.txt", testResult.get(1));
    }

    @Test
    public void testMetaDataCreate() throws IOException {

        ArrayList<String> testResult = proceedData.metaData(fileTxt);

        Assert.assertEquals("Data create: 2015-11-28T16:02:07.098671Z", testResult.get(2));
    }

    @Test(expected = NoSuchFileException.class)
    public void testMetaDataCreateException() throws IOException {

        ArrayList<String> testResult = proceedData.metaData("rrr");

        Assert.assertEquals("Data create: 2015-11-28T16:02:07.098671Z", testResult.get(2));
    }
}
