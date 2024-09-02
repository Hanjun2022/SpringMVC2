package hello.login.domain.member;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={} ",member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    //로그인 아이디 찾기
    public Optional<Member> findByLoginid(String loginid){
//        List<Member>all=findAll();
//        for (Member member : all) {
//            if(member.getLoginId().equals(loginid)){
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty(); 값이 없으면 NULL을 반환한다.
//        //리스트를 스트림으로 바꿈
        return findAll().stream()
                .filter(m->m.getLoginId().equals(loginid))
                .findFirst();
        //만족하는 것만 다음단계로 넘어간다.
        //같은 것들이 다음 조건으로 넘어감 그중에 첫번쨰가 나옴
        //람다 스트림 식이 따로 있다. 이거 현업에서 자주 쓴다.
        // Optional 자바 8 되게 많은 상황이 있다. Optional로 찾을 수있도록
    }



    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
