package springmvc.hello.basic.requestMapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
// url에따라 어떤 컨트롤러가 호출될것인가?

public class MappingController {

    @RequestMapping(value = {"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("hello-basic");
        return "ok";
    }
    //요청 매핑을 2개로 찍을 수 있다.RestController가 있으면 Http메시지바디에 문자 그대로 리턴한다.
    //완전 다른 URL이라도 같은 화면 출력
    //설정을 안해주면 REQUESTMAPPING 은 아무거나 해도 다 똑같다 . GET,POST,PUT,PATCH
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get-v1");
        return "ok";
    }


    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }
    //GETMapping을 보냄

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mapping-path userId={}", data);
        return "ok";
    }
    // /mapping2/url 이름을 직접적으로 PathVariable을 통해서 매칭을 해줘도 된다. 위에꺼는 변수로 이름 바꿔서 출력한다.
    @GetMapping("/mapping2/{userId2}")
    public String mappingPath2(@PathVariable String userId2) {
        log.info("mapping-path userId2={}", userId2);
        return "ok";
    }

    /**
     *
     * @pathvariable로 URL에서 값을 꺼낼 수 있다.
     * 다중 Mapping
     * userId 에 주문번호 100번
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mapping-path userId={}, orderId={}", userId, orderId);
        return "ok";
    }
    //파라미터에 mode=debug가 있어야 호출이된다.
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mapping-param");
        return "ok";
    }
    //헤더값에 mode는 debug를 추가시켜줘야한다.
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mapping-header");
        return "ok";
    }
    //콘텐트 타입에 따라 처리를 다르게 consumes 를 써줘야 한다. 헤더의 타입이 application json인 경우 해결이 된다.

    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mapping-consumes");
        return "ok";
    }
    //이거 강의 한번 찾아서 봐야 할 듯 text/html을 생성한다.
    //Accept 클라이언트가 이런 형식을 받아들 일 수 잇어 produces를 통해 설정해준다.
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mapping-produces");
        return "ok";
    }
}