package hello.login.web.login;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotEmpty
    private String loginId;
    //로그인 폼 id랑 passwordS
    @NotEmpty
    private String password;
}
