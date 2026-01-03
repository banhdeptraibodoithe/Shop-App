package org.restaurant.restaurant.dtos.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RegisterDTO {
    private String name;
    @NotBlank(message = "Phone number can't be blank!")
    private String phoneNumber;
    private String address;
    private Date dateOfBirth;
    @NotBlank(message = "Password can't be blank!")
    private String password;
    private String reTypePassword;
    private Long facebookId;
    private Long googleId;
    @NotNull(message = "Role ID required!")
    private Long roleId;
}
