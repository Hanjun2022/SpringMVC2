package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.arguementresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    //멤버 저장소에서 사람을 꺼내야 함
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;
    //@GetMapping("/")
    public String home() {
        return "home";
    }
    //home.html
    //@GetMapping("/")
    public String homeLogin(@CookieValue(name="memberId",required = false)Long memberId, Model model){
        //쿠키값이 없는 사람도 들어오기때문에 required=false로 해주자
        if(memberId==null){
            return "home";
        }
         //로그인
         Member loginMember= memberRepository.findById(memberId);
         if(loginMember==null){
             //멤버가 없으면 바로 집으로
             return "home";
         }
         model.addAttribute("member",loginMember);
         return "loginHome";

    }

  // @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){
       //세션 관리자에 저장된 회원 정보 조회 쿠키가 아닌 request로 받는다.
        Member member =(Member) sessionManager.getSession(request);

        //null이면 home으로
        if(member==null){
            return "home";
        }

        //아니면 member에 저장해서 뿌림
        model.addAttribute("member",member);
        return "loginHome";

    }
  // @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model){
       HttpSession session= request.getSession(false);
       if(session==null){
           return "home";
       }
        //처음 들어왔을때 세션이 생성되면 안돼 (값이 없기 때문에 값이 없으면 그냥 null )(메모리먹음)

        //세션 관리자에 저장된 회원 정보 조회
        Member loginmember =(Member) session.getAttribute(SessionConst.Login_MEMBER);

       //세션에 회원 데이터가 없으면 home
        if(loginmember==null){
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member",loginmember);
        return "loginHome";

    }
   //@GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name=SessionConst.Login_MEMBER,required = false) Member loginmember, Model model){

        //@SessionAttribute 세션에서 attribute를 그냥 꺼냄 ,required=false 세션 생성해서 없으면 null을 반환

        //세션에 회원 데이터가 없으면 home
        if(loginmember==null){
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member",loginmember);
        return "loginHome";

    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginmember, Model model){
        //요청매핑 핸들러 구조 다시봐라
        //@SessionAttribute 세션에서 attribute를 그냥 꺼냄 ,required=false 세션 생성해서 없으면 null을 반환
        //@Login을 이렇게 두면 ModelAttribute처럼 작동을 해서 무언가를 만들어 주어야한다.
        //세션에 회원 데이터가 없으면 home
        if(loginmember==null){
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member",loginmember);
        return "loginHome";

    }

}