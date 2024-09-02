package springmvc.hello.basic;

import lombok.Data;

@Data
// 게터와 세터 등등 라이브러리를 추가해줌
public class HelloData {
    private String username;
    private int age;

}
