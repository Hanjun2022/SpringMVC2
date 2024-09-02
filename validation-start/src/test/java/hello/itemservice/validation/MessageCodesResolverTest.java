package hello.itemservice.validation;

import com.sun.jdi.Field;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

    private final MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
    //MessageCodesResolver는 오류메시지를 모아서 출력을 해준다. (item,required)
    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        //messageCode가 출력이 된다.
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        Assertions.assertThat(messageCodes).containsExactly("required.item","required");
    }

    @Test
    void messageCodesResolverField(){
       String[]messageCodes=codesResolver.resolveMessageCodes("required","item","itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        Assertions.assertThat(messageCodes).containsExactly(
                "required.item.itemName","required.itemName","required.java.lang.String"
                ,"required");

    }
}
