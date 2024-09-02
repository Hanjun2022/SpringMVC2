package hello.itemservice.web.validation;


import hello.itemservice.domain.item.Item;
import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @ResponseBody
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){
        log.info("API 컨테이너 호출");

        if(bindingResult.hasErrors()){
            log.info("검증 오류 발생 errors={}",bindingResult);
            return bindingResult.getAllErrors();
            //FieldError가 ObjectError를 상속받았기 때문이다.
        }

        log.info("성공 로직 실행");
        return form;
    }

}
