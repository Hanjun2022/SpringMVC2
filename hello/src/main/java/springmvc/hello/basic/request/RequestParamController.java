package springmvc.hello.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springmvc.hello.basic.HelloData;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
       String username= request.getParameter("username");
       Integer age=Integer.parseInt(request.getParameter("age"));
       //요청한것들을 직접 이거는 resposne로 getWriter로 직접 값을 써준다.
       log.info("username 111 = {} ,age={} ",username,age);
       response.getWriter().write("OK");
    }

    //@ResponseBody=>RestController랑 같은 역할이다.  http 쿼리값하고 연결됨
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age){
        log.info("username={},age={}",username,age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age)
    {
        log.info("username={},age={}",username,age);
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String name,
         int age){
        log.info("username={},age={}", name,age);
        return "OK";
    }
    //String,int,Integer면 @ReqeustParam도 생략가능
    //v4 : @RequestParam 을 생략한 케이스이다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true)String username,
            @RequestParam(required = false) Integer age){
        //required먄 깂이 true면 무조건 있어야 한다.
        //username= "" 이거는 가능 아예없애면 문제 생김
        //responsebody null ""
        log.info("username={},age={}", username,age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest")String username,
            @RequestParam(defaultValue="-1") int age){
//Integer에는 null 이 가능 int는 null이 안 들어간다.

        log.info("username={},age={}", username,age);
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));

    return "IOK";
    }
    //파라미터를 가지고 객체에 값을 넣어주는 기능
    /*
    @ResponseBody
    @RequestBody("/model-attribute-v1")
    public String modelAttribute(@RequestParam String username,@ReqeustParam int age){
    HelloData hellodata=new HelloData();
    hellodata.setUsername(username);
    hellodata.setAge(age);
    return "OK"
    }

     */
    //객체로 받는 방법
//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@ModelAttribute HelloData helloData){
//        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
//
//        return "ISAC";
//    }
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={} ,age= {} ",helloData.getUsername(),helloData.getAge());
        return "ISACS";
    }
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ISAC";
    }
    //생략도 가능하다.





    //http 요청이름


}
