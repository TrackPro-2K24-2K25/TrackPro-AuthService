package org.youcode.trackproauthservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "users", schema = "auth")
public class AuthUserEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private String email;

    @Column(name = "encrypted_password")
    private String encryptedPassword;
}
