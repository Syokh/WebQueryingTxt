package education.jsonlogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestHandlerJson {

    private HandlerJson handlerJson;

    @Before
    public void testCheck() {
        handlerJson = new HandlerJson();

    }

    @Test
    public void testCheckJsonObjectTrue() {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("limit", "5555");
        hashMap.put("length", "");

        Map<String, Integer> check = handlerJson.checkJsonObject(hashMap);
        Integer limit = check.get("limit");
        Integer length = check.get("length");

        Integer integerLimit = 5555;
        Integer integerLength = null;

        Assert.assertEquals(integerLimit, limit);
        Assert.assertEquals(integerLength, length);
    }

    @Test(expected = NullPointerException.class)
    public void testCheckJsonObject() {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("limit", "");

        Map<String, Integer> check = handlerJson.checkJsonObject(hashMap);
        Integer limit = check.get("limit");

        Integer integerLimit = 10000;

        Assert.assertEquals(integerLimit, limit);
    }

    @Test(expected = NumberFormatException.class)
    public void testCheckJsonObjectFalse() {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("limit", "Vetal");
        hashMap.put("length", "");

        Map<String, Integer> check = handlerJson.checkJsonObject(hashMap);
        Integer limit = check.get("limit");
        Integer length = check.get("length");

        Integer integerLimit = 10000;
        Integer integerLength = null;

        Assert.assertEquals(integerLimit, limit);
        Assert.assertEquals(integerLength, length);
    }

}
