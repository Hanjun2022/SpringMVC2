package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class ApiExceptionV3Controller {

    //Api 호출 하는 컨트롤러
    //@GetMapping("/api3/members/{id}")
    public ApiExceptionV3Controller.MemberDto getMember(@PathVariable("id")String id){
        if(id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
            //api 가 에러가 터짐
        }
        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }
        return new ApiExceptionV3Controller.MemberDto(id,"hello"+id);
    }
   // @GetMapping("/api3/response-status-ex1")
    public String responseStatusEx1(){
        throw new BadRequestException();
    }
    @GetMapping("/api3/response-status-ex2")
    public String responseStatusEx2(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"error.bad",new IllegalArgumentException());
    }

    //@GetMapping("/api3/default-handler-ex")
    public String defaultException(@RequestParam Integer data){
        return "ok";
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
