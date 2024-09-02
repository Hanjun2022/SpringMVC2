package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //Item==clazz(clazz타입과 같냐?)
        //Item==subItem(자식 클래즈여도 통과합니다.)
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item=(Item) target;
        if (!StringUtils.hasText(item.getItemName())) {
            //bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, "상품 이름은 필수입니다."));
            errors.rejectValue("itemName","required");
            //  ValidationUtils.rejectIfEmptyOrWhitespace();
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            //bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, "가격은 1,000~1,000,000까지 허용됩니다."));
            errors.rejectValue("price","range",new Object[]{1000,1000000},null);
            //마지막에는 defaultMessage이다.
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            // bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, "수량은 최대 9,999까지 허용됩니다."));
            errors.rejectValue("quantity","max",new Object[]{9999},null);
        }

        // 특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
                errors.reject("totalPriceMin",new Object[]{1000,resultPrice},null);
            }
        }


    }
}
