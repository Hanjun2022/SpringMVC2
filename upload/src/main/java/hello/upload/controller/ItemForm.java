package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;
    private String itemName;
    //Multipart로 form에서는 다 받아주어야 한다.
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;

}
