package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    private final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{
            if(ex instanceof UserException  ){
                log.info("UserExcetpion resolver to 400");
                //헤더가 json이냐 아니냐 로 나누어야 한다.
                String acceptHeader=request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if("application/json".equals(acceptHeader)){
                    Map<String,Object> errorResult=new HashMap<>();
                    errorResult.put("ex",ex.getClass());
                    errorResult.put("message",ex.getMessage());

                    String result=objectMapper.writeValueAsString(errorResult);
                    //json 객체를 문자로 해서 response에 넣어준다.
                    log.info("여기까지 오는 건가!");

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);

                    return new ModelAndView();
                    //정상적으로 리턴이 된다.
                }
                else{
                    //TEXT/HTML
                    return new ModelAndView("error/500");
                }
            }

        }
        catch (Exception e){
            log.error("resolver ex",e);
        }

        return null;
    }
}
