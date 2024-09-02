package springmvc.hello.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
//응답을 글자로 테스트자나
public class RequestHeaderController {
    //여러가지를 파라미터로 받아 처리할 수 있다.
    //Locale 언어정보
    //헤더정보 RequestHeader 로 header 정보를 받음
    //헤더 2개,1개  쿠키값도 받을 수 있다.
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod,
                          Locale locale, @RequestHeader MultiValueMap<String,String>headerMap
    , @RequestHeader("host")String host, @CookieValue(value="myCookie",required = false)String cookie){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }

}
