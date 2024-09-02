package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    //서블릿의 필터

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("처음 초기화예요 log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter dofFIlter 시발");
                //요청올때마다 생성됨
        //ServletRequest 기능이 없음
        HttpServletRequest httpRequest=(HttpServletRequest) request;
        String requestURI=httpRequest.getRequestURI();

        String uuid= UUID.randomUUID().toString();
        //요청온것을 구분하기 위해 만듬
        try{
            log.info("Request :[{}][{}]",uuid,requestURI);
            //요청 로그호출
            chain.doFilter(request,response);
            //다음 필터가 있으면 필터가 호출 없으면 서블릿이 호출
        }catch(Exception e){
            throw e;
        }finally{
            log.info("FINALLY Response :[{}][{}]",uuid,requestURI);
            //자바 finally 항상 호출이된다.
        }


    }

    @Override
    public void destroy() {
        log.info("종료 log filter destroy");
    }
}
