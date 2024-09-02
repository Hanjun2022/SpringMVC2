package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

@Slf4j
public class LoginCheckFilter implements Filter {

private static final String [] whitelist={"/","/member/add","/login","/logout","/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httprequest=(HttpServletRequest)request;
        String RequestURI=httprequest.getRequestURI();

        HttpServletResponse httpResponse=(HttpServletResponse) response;

        try{
            log.info("인증 체크 필터 시작{}",RequestURI);
            if(isLoginCheckPath(RequestURI)){
                //화이트 리스트에 포함이 안되엉 있다면.
                log.info("인증 체크 로직 실행:{}",RequestURI);
                HttpSession session=httprequest.getSession(false);
                if(session==null || session.getAttribute(SessionConst.Login_MEMBER)==null){
                    log.info("미인증 사용자 요청{}",RequestURI);
                    //로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL="+RequestURI);
                    return ;//더이상 서블릿,컨트롤러는 더는 호출되지 않는다.
                }
            }
            //false가 나면 이게 실행됨
            chain.doFilter(request,response);
        }catch (Exception e){
            throw e; //예외도 로깅으로 남길 수 있다.
        }finally {
            log.info("인증 체크 필터 종료:{}",RequestURI);
        }
    }
/**
 * 화이트 리스트의 경우 인증 체크X
 *
 */
private boolean isLoginCheckPath(String requestURI){
    return !PatternMatchUtils.simpleMatch(whitelist,requestURI);
    // PatternMatchUtils 이거 안쓰면 로직이 매우 복잡해진다. 단순하게 매칭이 되던가?

}

}
