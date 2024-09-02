package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "error.bad")
public class BadRequestException extends RuntimeException{
//상태코드를 바꿈  500->400 으로 바꿈
}
