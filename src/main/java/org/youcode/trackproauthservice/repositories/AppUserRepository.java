package org.youcode.trackproauthservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.youcode.trackproauthservice.domain.AppUser;
import org.youcode.trackproauthservice.domain.enums.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    //    Boolean existsByEmail(String email);

//    Optional<AppUser> findByEmail(String email);


    Optional<AppUser> findByResetToken(String resetToken);
    List<AppUser> findByRole(Role role);

    UserDetails getUserById(UUID id);
    Optional<AppUser> findByFirstName(String nom);


}
