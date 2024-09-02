package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j

public class MyHandlerExceptionResolver implements  HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call  resolver ",ex);

        try{
            if(ex instanceof  IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                //예외를 400으로 바꾸어줌 (서블릿 컨테이너에 알려줌)
                return new ModelAndView();
                //빈 값으로 넘기면 return return 서블릿까지 된다. 새로운 ModelAndView
//                if(ex ..){
//                    response.getWriter().println("{SDsd");
//                }
            }
        } catch(IOException e){
          log.error("resolve Ex",ex);
        }


        return null;
        //그냥 예외가 터져서 날라간다.
    }
}