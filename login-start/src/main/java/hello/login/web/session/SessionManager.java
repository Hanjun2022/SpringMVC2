package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */
@Component
public class SessionManager {
    //세션저장소

    private static final String SESSION_COOKIE_NAME="mySessionId";
    private Map<String,Object>sessionStore=new ConcurrentHashMap<>();
    //동시 접근을 막는다.
    /*
        세션 생성
     */
    public void createSession(Object value, HttpServletResponse response){
        //session Id 생성
        String sessionId=UUID.randomUUID().toString();
        sessionStore.put(sessionId,value);

        //쿠키생성
        Cookie mySessionCookie= new Cookie(SESSION_COOKIE_NAME,sessionId);
        response.addCookie(mySessionCookie);
        //세션을 만들고 response로 보낸다.
    }

    /**
     *세션 조회
     */

    public Object getSession(HttpServletRequest request){
//        Cookie []cookies=request.getCookies();
//        if(cookies==null){
//            return null;
//        }
//        for(Cookie cookie:cookies){
//            if(cookie.getName().equals(SESSION_COOKIE_NAME)){
//                return sessionStore.get(cookie.getName());
//            }
//        }   return null;(못 찾으면 null)
        Cookie sessionCookie=findCookie(request,SESSION_COOKIE_NAME);
        if(sessionCookie==null){
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
        //멤버객체가 반환이 된다.
    }

    //쿠키 찾는 부분이 어렵다.
    public Cookie findCookie(HttpServletRequest request,String cookieName){
        //쿠키가 배열로 반환이 된다. (무조건 무조건이야)
        Cookie[]cookies=request.getCookies();
        if(cookies==null){
            return null;
        }
       return  Arrays.stream(cookies).filter(cookie->cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
                //배열을 stream으로 바꾸어 준다. 파라미터로 넘어온 cookieName과 같으면 그다음 과정으로 넘어간다.
                // 쿠기가 하나만 있으면 바로 반환한다. stream이라는 게 들어있는 값을 하나씩 넘겨서 확인한다.
                //순서중에 하나를 찾으면 바로 반환
    }

    /**
     * 세션 만료
     * 쿠키를 가져온 다음에
     * null이 아니면 (찾아다면)
     * 저장소에서 지운다.ㄴ
     */
    public void expire(HttpServletRequest request){
        Cookie sessionCookie=findCookie(request,SESSION_COOKIE_NAME);
        //쿠키를 가져온다.
        if(sessionCookie!=null){
            sessionStore.remove(sessionCookie.getValue());
            //세션스토어에 저장된 걸  다 지워진다. (표에서 한 줄 삭제 개념 실현)
        }
    }
}
