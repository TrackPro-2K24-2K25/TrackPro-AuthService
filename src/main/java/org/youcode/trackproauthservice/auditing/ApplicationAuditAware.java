package org.youcode.trackproauthservice.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.youcode.trackproauthservice.domain.AppUser;

import java.util.Optional;
import java.util.UUID;

public class ApplicationAuditAware implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        AppUser userPrincipal = (AppUser) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }
}
