package hello.login.web.arguementresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParamenter:실행");

        boolean hasLoginAnnotation=parameter.hasMethodAnnotation(Login.class);
        //파라미터에 이런 어노테이션이 있니? HomeController에 @Login
        boolean hasMemberType=Member.class.isAssignableFrom(parameter.getParameterType());
        //parameter @Login Member이냐?
        return hasMemberType && hasLoginAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        log.info("resovleArguement 실행");
        HttpServletRequest request=(HttpServletRequest) nativeWebRequest.getNativeRequest();
        HttpSession session=request.getSession(false);
        if(session==null){
            return null;
            //@Login에 null을 넣는다.
        }
        Object Memeber=session.getAttribute(SessionConst.Login_MEMBER);
        //반환된 멤버가 들어간다.
        return Memeber;
    }
}
