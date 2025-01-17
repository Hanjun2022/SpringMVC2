package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    //컨트롤러가 서비스를 호출한다는 개념인가?
    private  final LoginService loginService;
    private final SessionManager sessionManager;
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm form){
        return "login/loginForm";
    }

  // @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";}
        //성공 로직  폼에서 로그인 ID,폼에서 로그인 password 를 꺼내서 Member 를 꺼낸다.
        //오률 bindingResult를 낸다.
        Member loginMember=loginService.login(form.getLoginId(),form.getPassword());
        if(loginMember ==  null){
            //특정 필드에 관한것이 아니다.
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            //전체 오류가 터진다.
            return "login/loginForm";
        }
        //로그인 성공 처리 TODO
        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
       Cookie idcookie= new Cookie("memberId",String.valueOf(loginMember.getId()));
        response.addCookie(idcookie);
        return "redirect:/";
    }

   //@PostMapping("/logout")
    public String logout(HttpServletResponse response){
        expireCookie(response,"memberId");
        return "redirect:/";
    }


    //@PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }


    private void expireCookie(HttpServletResponse response,String cookieName){
        Cookie cookie=new Cookie(cookieName,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
  //  @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember=loginService.login(form.getLoginId(),form.getPassword());
        if(loginMember ==  null){
            //특정 필드에 관한것이 아니다.
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리 TODO

        //세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
        sessionManager.createSession(loginMember,response);
        return "redirect:/";
    }

  //  @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember=loginService.login(form.getLoginId(),form.getPassword());
        if(loginMember ==  null){
            //특정 필드에 관한것이 아니다.
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        //로그인 성공 처리 TODO
        //세션이 있으면 있는 세션 반환,없으면 신규 세션을 생성 (request가 필요하다.)
       HttpSession session= request.getSession(true);
        //세션에 로그인 회원 정보 보관 (인터페이스 상수,객체)
        session.setAttribute(SessionConst.Login_MEMBER,loginMember);

        //세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
      //  sessionManager.createSession(loginMember,response);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form,
                          @RequestParam(defaultValue = "/")String redirectURL,
                          BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember=loginService.login(form.getLoginId(),form.getPassword());
        if(loginMember ==  null){
            //특정 필드에 관한것이 아니다.
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리 TODO
        //세션이 있으면 있는 세션 반환,없으면 신규 세션을 생성 (request가 필요하다.)
        HttpSession session= request.getSession(true);
        //세션에 로그인 회원 정보 보관 (인터페이스 상수,객체)
        session.setAttribute(SessionConst.Login_MEMBER,loginMember);

        //세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
        //  sessionManager.createSession(loginMember,response);
        return "redirect:"+redirectURL;
    }
     @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        //세션이 없더라도 널을 반환해라
        if(session!=null){
            session.invalidate();
            //세션 초기화버튼
        }
        return "redirect:/";
    }


}
