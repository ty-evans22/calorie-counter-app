package coms309.backend.roundtrip.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class LoginVO {
	private @Getter @Setter String email;
    private @Getter @Setter String password;
}
