package org.youcode.trackproauthservice.Payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private String message;

    // Constructor for only access token
    public AuthenticationResponse(String accessToken) {
        this.access_token = accessToken;
    }

    // You can also create a constructor for refresh token if needed
    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.access_token = accessToken;
        this.refreshToken = refreshToken;
    }
}
