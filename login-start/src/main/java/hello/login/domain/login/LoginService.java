package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;
    /**
     * return null 로그인 실패
     */
    public Member login(String loginId,String password){
//        Optional<Member>findMemberOptional =memberRepository.findByLoginid(loginId);
//
//        Member member=findMemberOptional.get();
        //저장되어있는 인간과 같으면  Optional ->get 모든 게 터져서 나옴
//        if(member.getPassword().equals(password)){
//            return member;
//        }else{
//            return null;
//        }

        //저장되어있는 인간인가?  앞에서 만든 로그인 된 ID인가?
        Optional<Member>byLoginId=memberRepository.findByLoginid(loginId);
        return memberRepository.findByLoginid(loginId)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
        /**
         * Optional (로그인 실패)
         */
    }
}
