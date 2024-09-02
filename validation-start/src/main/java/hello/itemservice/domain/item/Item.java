package hello.itemservice.domain.item;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;


@Data

public class Item {

    //@NotNull(groups= UpdateCheck.class)
    private Long id;


  // @NotBlank(groups ={UpdateCheck.class, SaveCheck.class})
    private String itemName;

//    @NotNull(groups={UpdateCheck.class, SaveCheck.class})
//    @Range(min=1000,max=1000000)
    private Integer price;

//    @NotNull(groups={UpdateCheck.class, SaveCheck.class})
//    @Max(value=9999,groups={SaveCheck.class})
    private Integer quantity;


    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
