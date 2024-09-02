package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


//@Component
//호출하는 부분 인가? 오류 화면을 등록해줘야 움직임 -  ㅊㅌ                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           컴포넌트 등록해줘야 움직임
//서블릿이 제공하는 기본 오류 매커니즘이 무엇이냐? (새로운 에러 페이지를 제공한다.)
public class  WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //기본 오류 페이지를 커스톰한다.
        ErrorPage errorPage404=new  ErrorPage(HttpStatus.NOT_FOUND,"/error-page/404");
        //404가 발생하면 여기로 호출해라 .
        ErrorPage errorPage500=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error-page/500");
        ErrorPage errorpageEx=new ErrorPage(RuntimeException.class,"/error-page/500");
        //RuntimeException이 발생하면 뒤에 URL로 이동해라.
        //두개는 상태 코드 exception이 발생하였을 때 (자식 타입의 에러코드도 다 보내준다.)
        factory.addErrorPages(errorPage500,errorPage404,errorpageEx);

        //spring이 제공함
    }
}
