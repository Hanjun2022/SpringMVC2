package springmvc.hello.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseVIewController {
    //동적 뷰 생성이 됨
    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1(){
        ModelAndView mav=new ModelAndView("response/hello");
                mav.addObject("data","HELLO!!");
        return mav;
    }
    //동적 뷰를 생성하는 루트

    @RequestMapping("/response-view-v2")
    public String responseVIew2(Model model){
        model.addAttribute("data","hello!");
        return "response/hello";
    }

    //@Controller ->String 뷰의이름이다. @ResponseBody를 메시지바디에 데이터를 입력한거라 ㅈ됨

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello!");
    }
    //2번째꺼 이게 실제코드는 뷰를 찾아주고 거기로 데이터가 전달됨 @ResponseBody쓰면 문자가 그대로 뷰에 출력됨
    //3번째 꺼는 권장하지 않는다.

}
