package springmvc.hello.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller

public class RequestBodyStringController {
    //Http메시지 바디에 직접 데이터를 넣는 경우
    //가장 단순한 텍스트 메시지를 Http메시지 바디에 담아서 전송하고 읽어보자
    //Http메시지 바디에 InputStream을 이용해서 값을 넣어보자
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream= request.getInputStream();
        String messagebody=StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //어떤 인코딩을 이용해서 해석을 해 줄 것인가.

        log.info("message Body={}",messagebody);
        response.getWriter().write("OKI");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
//파라미터로 받을 수 있는 것들 사이트에 나와있다.
        String messagebody=StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message Body={}",messagebody);
        responseWriter.write("OKI@S");
    }

    //String으로 받지 않고 직접적으로 해결
    //HttpEntity (메시지 컨버터가 처리해줌)  String으로 반환

    @PostMapping("/request-body-string-v3")
    public  HttpEntity<String>requestBodyStringV3 (HttpEntity<String>httpEntity) throws IOException {
        //RequestEntity
        String messageBody=httpEntity.getBody();
        String header= String.valueOf(httpEntity.getHeaders());
        log.info("messageBody={} ",messageBody);
        log.info("headers={}",header);
        //return new ResponseEntity<String>("OK@#", HttpStatus.CREATED);
        return new HttpEntity<>("ok");
        //첫번째에 파라미터를 넣을 수 있다.
    }

//    @PostMapping("/request-body-string-v4")
//    public HttpEntity<String> requestBodyStringV4(@RequestBody  String meesagebody){
//        log.info("messageBOdy={}",meesagebody);
//        return new HttpEntity<>("OKREQUESTBODY");
//    }
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody  String meesagebody){
        log.info("messagebody={}",meesagebody);
        return "Sa연";
    }
    //HttpEntity나 이렇게 글자로 쓴다. Http메시지바디를 조히하는 기능 @RequestBody
    //메시지컨버터가 HttpEntity 등등을 문자나 객체로 바꾸어주는 역할을 수행한다.


}
