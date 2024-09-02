package springmvc.hello.basic.request;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.hello.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello","age":20}
 * content-type:applicatopn/json
 */

@Slf4j
@Controller
public class RequestJsonController {
    private ObjectMapper objectMapper=new ObjectMapper();
    //자바 객체 ->json json ->자바 객체로 바꿈

    @RequestMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //objectMapper.readValue로 값을 변환한다. 내용,타입
        log.info("helloData={}", helloData);
        log.info("messagebody={}",messageBody);
        log.info("helloData username={},age={}",helloData.getUsername(),helloData.getAge());
        response.getWriter().write("ok");
    }

   @ResponseBody
    @RequestMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData);
        return "V2 OK";
    }

    @ResponseBody
    @RequestMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        // 생략하면 ModelAttribute를 쓰게 된다.
        //요청 파라미터를 처리할려면 @RequestBody를 써야한다.
        log.info("helloData={}", helloData);
        return "V3 ok";
    }
    //HelloData 를 직접적으로 꺼내준다.

//    @RequestMapping("/request-body-json-v4")
//    public HttpEntity<String> requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
//        HelloData helloData = httpEntity.getBody();
//        //직접 꺼내서 출력해야 한다.
//        log.info("helloData={}", helloData);
//        return new HttpEntity<>("V4 ok");
//    }
    @RequestMapping("/request-body-json-v4")
    public HttpEntity<String>requestBodyJson4(HttpEntity<HelloData>helloData){
        HelloData helloData1=helloData.getBody();
        log.info("HelloData ={}",helloData1);
        return new HttpEntity<>("HI V4 Completed");
    }

    @ResponseBody
    @RequestMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("helloData={}", helloData);
        //들어온 것을 그대로 HelloData로 처리는 가능  생성된 객체 json으로 바뀐게 응답으로 바뀌고 http메시지 바디에 박힌다.
        return helloData;
    }


}
