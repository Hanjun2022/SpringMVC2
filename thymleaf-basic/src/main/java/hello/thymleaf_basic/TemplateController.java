package hello.thymleaf_basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
@Slf4j
public class TemplateController {

    @GetMapping("/fragment")
    public String template() {
        log.info("sd");
        return "template/fragment/fragmentMain";
    }
    //하나하나 넘기는 거
    @GetMapping("layout")
    public String layout(){
        return "template/layout/layoutMain";
    }
    //레이아웃으로 넘기는 거
    @GetMapping("/layoutExtend")
    public String layoutExtend(){
        return "template/layoutExtend/layoutExtendMain";
    }

}
