package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        // 한국어 로케일을 명시적으로 지정
        String result = ms.getMessage("hello", null, Locale.KOREA);
        Assertions.assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        // 잘못된 메시지 코드를 요청했을 때 예외가 발생하는지 테스트
        Assertions.assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        // 메시지를 찾을 수 없을 때 기본 메시지를 반환하는지 테스트
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        Assertions.assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        // 인자를 포함한 메시지 처리 테스트
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        Assertions.assertThat(message).isEqualTo("안녕 Spring");
    }
    @Test
    void defaultLang(){
        Assertions.assertThat(ms.getMessage("hello",null,null)).isEqualTo("안녕");
        Assertions.assertThat(ms.getMessage("hello",null,Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enlang(){
        Assertions.assertThat(ms.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }


}
