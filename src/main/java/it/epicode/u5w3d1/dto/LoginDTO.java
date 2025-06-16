package it.epicode.u5w3d1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}



