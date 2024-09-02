package hello.typeconverter.formatter;


import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionTest {

    @Test
    void formattionConversionTest(){
        DefaultFormattingConversionService conversionservice=new DefaultFormattingConversionService();
        //컨버터를 먼저 등록해 보자
        conversionservice.addConverter(new StringToIpPortConverter());
        conversionservice.addConverter(new IpPortToStringConverter());

        //포매터를 먼저 등록해보자
        conversionservice.addFormatter(new MyNumFormatter());

        //컨버터 사용
        IpPort ipPort=conversionservice.convert("127.0.0.1:8080", IpPort.class);
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1",8080));

        //포매터 사용
        String convert =conversionservice.convert(1000,String.class);
        Assertions.assertThat(conversionservice.convert(1000,String.class)).isEqualTo("1,000");
        Assertions.assertThat(conversionservice.convert("1,000",Long.class)).isEqualTo(1000L);

    }
}
