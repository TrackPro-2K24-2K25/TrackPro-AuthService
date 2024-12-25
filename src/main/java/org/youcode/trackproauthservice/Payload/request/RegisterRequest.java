package org.youcode.trackproauthservice.Payload.request;

import org.youcode.trackproauthservice.domain.enums.Role;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
}
