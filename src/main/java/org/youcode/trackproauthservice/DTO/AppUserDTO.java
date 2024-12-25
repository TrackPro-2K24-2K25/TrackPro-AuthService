package org.youcode.trackproauthservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.youcode.trackproauthservice.domain.enums.Role;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AppUserDTO {
    private String id;
    private String email;
    private String password;
    private String firstName;;
    private String lastName;
    private String phoneNumber;
    private Role role;

    public AppUserDTO(UUID id, String email, String motDePasse) {
    }
}
