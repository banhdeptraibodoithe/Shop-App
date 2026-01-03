package org.restaurant.restaurant.dtos.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    @NotBlank(message = "Phone number can't be blank!")
    private String phoneNumber;
    @NotBlank(message = "Password can't be blank!")
    private String password;
}
