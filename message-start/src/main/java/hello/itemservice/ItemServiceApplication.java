package hello.itemservice;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@SpringBootApplication

public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);


	}

//	@Bean
//	public MessageSource messageSource(){
//		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
//		messageSource.setBasenames("messages","errors");
//		//프로퍼티스를 읽는다.
//		messageSource.setDefaultEncoding("UTF-8");
//		return messageSource;
//	} 안써줘도 자동으로 등록을 해준다.

}
