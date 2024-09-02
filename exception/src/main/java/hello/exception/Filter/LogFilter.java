package hello.exception.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter ");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("Request : [{}] ,[{}], [{}]", uuid, request.getDispatcherType(),requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.info("exception :" ,e.getMessage());
            throw e;
        } finally {
            log.info("FINALLY Response : [{}][{}]", uuid, requestURI);
        }
    }
}
