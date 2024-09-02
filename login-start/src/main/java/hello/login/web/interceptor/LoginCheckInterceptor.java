package hello.login.web.interceptor;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI=request.getRequestURI();
        log.info("인증 체크 인터셉트 실행 {}",requestURI);

        HttpSession session=request.getSession();
        if(session==null || session.getAttribute(SessionConst.Login_MEMBER)==null){
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login?redirectURL="+requestURI);
            return false;
        }
        //LoginCheckFilter와 달리 등록할때 복잡한 과정을 다 처리한다.


        //굉장히 중요하다.
        return true;
    }
}
