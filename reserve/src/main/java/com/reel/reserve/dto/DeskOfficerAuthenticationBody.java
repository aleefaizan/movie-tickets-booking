package com.reel.reserve.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeskOfficerAuthenticationBody {

    @NotNull(message = "Email must not be null.")
    @Email
    private String email;

    @NotNull(message = "Password must not be null.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
    private String password;

}
