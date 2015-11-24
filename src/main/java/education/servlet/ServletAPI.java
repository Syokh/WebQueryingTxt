package education.servlet;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import education.read.ProceedData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServletAPI extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String fileName = "D:\\MyProb\\17,11,15\\src\\resources\\myFile.txt";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProceedData proceedData = new ProceedData();

        List<Object> result = new ArrayList<Object>();

        StringBuffer jb = new StringBuffer();
        String line;
        Integer limit;
        Integer length = null;

        ArrayList arrayList = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(jb.toString());
            String limit1 = (String) jsonObject.get("limit");
            if (limit1.equals("")) {
                limit = 10000;

            } else {
                limit = Integer.parseInt(limit1);
            }
            String q = (String) jsonObject.get("q");
            String length1 = (String) jsonObject.get("length");
            if (!length1.equals("")) {
                length = Integer.parseInt(length1);
            }

            String includeMetaData = (String) jsonObject.get("includeMetaData");
            ArrayList<String> stringArea = proceedData.find(fileName, q, limit, length);
            result.add(stringArea);

            if (includeMetaData.equals("true")) {
                arrayList = proceedData.metaData(fileName);
                result.addAll(arrayList.subList(0, 3));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.getWriter().print(json);

    }
}
