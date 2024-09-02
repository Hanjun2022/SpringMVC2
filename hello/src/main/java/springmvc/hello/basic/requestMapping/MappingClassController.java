package springmvc.hello.basic.requestMapping;


import org.springframework.web.bind.annotation.*;

@RestController
//springweb
@RequestMapping("/mapping/users")
//공통적으로 처리를 해 준다.
public class MappingClassController {

    @GetMapping
    public String user(){
        return "get users";
    }
    @PostMapping
    public String addUser(){
        return "post user";
    }
    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String id){
        return "get UserId="+id;
    }
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update UserId="+userId;
    }
    //수정
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete UserId="+userId;
    }
    //삭제
    //실질적으로 데이터를 어떻게 클라리언트 ㅅ
}
