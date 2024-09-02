package hello.login.web.session;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session==null){
            return "세션이 왔습니다.";
        }
        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name->log.info("session.name= {} , value={} ",name,session.getAttribute(name)));

        log.info("sessionId={}",session.getId());
        //getMaxInactiveInterval 은 무엇일까?
        log.info("session.getMaxInactive={}",session.getMaxInactiveInterval());
        //기본이 Long이라 시간으로 바꾸기 위해서 new Date를 붙여준다.
        log.info("creationTime={}",new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}",new Date(session.getLastAccessedTime()));
        //따끗따끗한 session이니?
        log.info("isNew={}",session.isNew());

        return "세션 출력";

    }
}
