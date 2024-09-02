package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class SerlvetExController {

    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("내가 만든 Runtime 예외 발생!");
        //Exception이 터져 500을 내보낸다.
    }
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 오류");
        //Was로 보냄 was가 확인하고 설정한 오류코드를 보여준다.메시지도 옵션을 바꾸면 출력이 가능하다.
    }
    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400,"400 오류");
        //이 번호 자체는 등록이 되어 있다.
    }

    @GetMapping("/error-500")
    public void error505(HttpServletResponse response)throws IOException{
        response.sendError(500);
    }
}
