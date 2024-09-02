package hello.login.web.arguementresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//@Target 여러가지
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//Retention RUNTIME을 주로 쓸 것이다.
public @interface Login {
}
