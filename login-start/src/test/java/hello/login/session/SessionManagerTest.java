package hello.login.session;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//public 없어도 된다.
public class SessionManagerTest {
    SessionManager sessionManager= new SessionManager();
    @Test
    //서버에서 클라이언트로
    void sessionTest(){
        //세션 생성 Mock (스프링 가짜로 이런 기능 테스트)
        MockHttpServletResponse response=new MockHttpServletResponse();
        Member member=new Member();
        sessionManager.createSession(member,response);
        //세션이 만들어짐 반응을 만들어 session을 담아서 준다. 서버에서 정보가나가는 과정

        //요청에 응답 쿠기 저장 (zmf->서 요청에 세션 id를 담는 과정)
        MockHttpServletRequest request=new MockHttpServletRequest();
        request.setCookies(response.getCookies()); //mySessionId=1231231-12312-qweqwe

        //세션조회 (클라이언트에서 서버로 갔을 떄 조회가 되나?)
        Object result=sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired=sessionManager.getSession(request);
        Assertions.assertThat(expired).isNull();

    }

}
