package hello.login;

import hello.login.web.arguementresolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //로그인 인터셉터는 WebMvcConfiguer 이거 뒤에 써야 한다.
    //ArgumentResolver를 넣는다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).order(1).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/*.ico","/error");
        //excludePath 된거는 호출되지 않는다.

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add","/login","/logout"
                        ,"/css/**","/*.ico","/error");

        // /**이건 모든것을 허용하겠다.
    }
//스프링이제공하는 기능이다. 요 방식으로 제공을 해준다.


    //필터를 등록한다.

    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter>filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/");
        //모든 요청에 필터가 적용된다.
        return filterRegistrationBean;
    }
   @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter>filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        //모든 요청에 필터가 적용된다.
        return filterRegistrationBean;
    }
}
