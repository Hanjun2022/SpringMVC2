package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionService {

    @Test
    void conversionService(){
        //등록
        DefaultConversionService conversionService=new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        //사용
        Integer result=conversionService.convert("10",Integer.class);
        System.out.printf("result ="+result);
       // Assertions.assertThat(conv)
    }
}
