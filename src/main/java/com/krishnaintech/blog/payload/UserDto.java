package com.krishnaintech.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private int id;
    @NotEmpty()
    @Size(min = 4, message = "username should be minimum of 4 characters")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty()
    @Size(min = 3, max = 8, message = "Password should be minimum of 3 and maximum of 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{3,8}$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    private String password;
    @NotEmpty(message = "about is empty")
    private String about;
}
