package lk.ijse.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String id;
    @NotBlank(message = "User First name cannot be blank")
    private String firstName;
    @NotBlank(message = "User Last name cannot be blank")
    private String lastName;
    @NotBlank(message = "User Email name cannot be blank")
    private String email;
    @NotBlank(message = "User Password name cannot be blank")
    private String password;
}
