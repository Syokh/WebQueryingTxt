package education.servlet;

import com.google.gson.Gson;
import education.jsonlogic.HandlerJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ServletAPI extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String fileName = "D:\\MyProb\\17,11,15\\src\\resources\\myFile.txt";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandlerJson handlerJson = new HandlerJson();

        Map<String, String> result = handlerJson.doResponse(request, fileName);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
