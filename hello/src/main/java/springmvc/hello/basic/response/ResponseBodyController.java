package springmvc.hello.basic.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springmvc.hello.basic.HelloData;

import java.io.IOException;

@Slf4j
@Controller

public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException{
        response.getWriter().write("OK");
    }
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV1() throws IOException{
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    //문자를 바디에 입력 Responsebody 안써도 똑같다.
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "OKV3";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData>responseBodyJsonV1(){
        HelloData helloData=new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData,HttpStatus.OK);
    }
    //ResponseEntity는 HttpStatus 상태를 지정가능 위에꺼 상태 를 넣어서 반환 가능


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData=new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
    // 그냥은 ResponseStatus를 이용해서 응답 가능하다.
}
