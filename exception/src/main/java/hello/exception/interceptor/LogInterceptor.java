package hello.exception.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;


@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID="logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI=request.getRequestURI();
        String uuid= UUID.randomUUID().toString();
        //밖에다 선언해서 넘기면 싱글톤이라 ㅈ될수도 있다.
        request.setAttribute(LOG_ID,uuid);
        //핸들러 어댑터전에 호출이 됨
        //@RequestMapping:HandlerMethod
        //정적 리소스:RessourceHttpRequestHandler
        if(handler instanceof HandlerMethod){
            HandlerMethod hm=(HandlerMethod) handler;
            //타입 캐스팅
        }
        log.info("REQUEST [{}],[{}],[{}],[{}]",uuid,request.getDispatcherType(),requestURI,handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}] }",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI=request.getRequestURI();
        String logId=(String)request.getAttribute(LOG_ID);
        log.info("FInally RESPONSE:[{}],[{}],[{}]",logId,requestURI,handler);

        if(ex!=null){
            log.error("afterCompletion err!!",ex);
            //오류는 이렇게 넣어라
        }
        //uuid를 받아서 사용할 수 있다. request하나의 사용자에 대해 같은게 보장이 된다.

    }
}
