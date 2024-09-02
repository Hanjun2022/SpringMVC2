package hello.login.domain.member;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;
    //DB에 들어가는 ID

    @NotEmpty
    private String loginId; //직접 입력 ID

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
