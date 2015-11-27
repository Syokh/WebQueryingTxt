package education.jsonlogic;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import education.read.ProceedData;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HandlerJson {

    public Map<String, String> doResponse(HttpServletRequest request, String fileName) throws IOException {

        ProceedData proceedData = new ProceedData();
        Map<String, String> result = new HashMap<String, String>();

        Map<String, String> jsonMap = getJsonObject(request);
        Map<String, Integer> mapJson = checkJsonObject(jsonMap);

        Integer limit = mapJson.get("limit");
        String q = jsonMap.get("q");
        Integer length = mapJson.get("length");
        String includeMetaData = jsonMap.get("includeMetaData");

        ArrayList<String> stringArea = proceedData.find(fileName, q, limit, length);
        result.put("text", stringArea.get(0));

        if (includeMetaData.equals("true")) {
            ArrayList<String> arrayList = proceedData.metaData(fileName);
            result.put("fileSize", arrayList.get(0));
            result.put("fileName", arrayList.get(1));
            result.put("fileCreationDate", arrayList.get(2));
        }

        return result;
    }

    public Map<String, Integer> checkJsonObject(Map<String, String> jsonMap) {

        Map<String, Integer> map = new HashMap<String, Integer>();

        String limit = jsonMap.get("limit");
        String length = jsonMap.get("length");

        Integer limit1;
        if (limit.equals("")) {
            limit1 = 10000;
        } else {
            limit1 = Integer.parseInt(limit);
        }

        Integer length1;
        if (!length.equals("")) {
            length1 = Integer.parseInt(length);
        } else {
            length1 = null;
        }

        map.put("limit", limit1);
        map.put("length", length1);

        return map;
    }

    public StringBuffer parsingJson(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jb;
    }

    public Map<String, String> getJsonObject(HttpServletRequest request) {

        StringBuffer stringBuffer = parsingJson(request);
        Map<String, String> jsonMap = new HashMap<String, String>();

        try {
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());

            String limit = (String) jsonObject.get("limit");
            String q = (String) jsonObject.get("q");
            String length = (String) jsonObject.get("length");
            String includeMetaData = (String) jsonObject.get("includeMetaData");

            jsonMap.put("limit", limit);
            jsonMap.put("q", q);
            jsonMap.put("length", length);
            jsonMap.put("includeMetaData", includeMetaData);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonMap;
    }
}
