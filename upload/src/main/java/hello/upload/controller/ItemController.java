package hello.upload.controller;

import hello.upload.domain.Item;
import hello.upload.domain.ItemRepository;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;
    //의존성을 주입한다.

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "item-form";
    }

    //저장이 되서 보여주는 로직 ItemForm 을 받음 redirect해줌
    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
        //upload된 파일
        // MultipartFile attachFile=form.getAttachFile()
        //UploadFile uploadFile=form.getImageFiles()
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        //aws는 s3에 저장이됨
        //파일은 storage에 저장 dbXX 데이터베이스에 저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        //redirect를 시험하기 위해 이렇게 item을 넣어둔다.

        return "redirect:/items/{itemId}";
    }
    //여기까지가 파일저장 로직이다.


    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id);
        //리다이렉트된 url로 가져와서 item을 model에 추가를 시켜주고 페이지 이동
        model.addAttribute("item", item);
        return "item-view";
    }

    //Spring에 resource 파일이미지 띄우는 법 파일을 스트림으로 가져온다. @Responsebody
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {

        //file: Users/../file/123-ads.png  UrlResource 가 찾아온다. 이경로에 있는 파일을 가져온다.
        //보안에는 체크 로직이 여러가지 더 있다. 레포지토리에
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    //첨부 파일 다운 다른 형태
    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();
        //업로드 할 때 내가 올린 파일 명을 가져오기
        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
        //이거거의 규약이다.

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
        //헤더를 안 넣고 다운 시 다운이 안 되고 적혀있는 파일만 보인다.
    }

}
