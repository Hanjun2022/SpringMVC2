package hello.servlet.domain.basic.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.domain.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="responseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-type:application-json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData=new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //{"username":"kin","age":"20}
       String result= objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}