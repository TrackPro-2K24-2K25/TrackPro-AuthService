package org.youcode.trackproauthservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.youcode.trackproauthservice.domain.AppUser;
import org.youcode.trackproauthservice.domain.Token;
import org.youcode.trackproauthservice.domain.enums.TokenType;
import org.youcode.trackproauthservice.repositories.AppUserRepository;
import org.youcode.trackproauthservice.repositories.TokenRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository utilisateurRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final String BEARER_PREFIX = "Bearer ";
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse register(RegisterRequest request) {
//        if (userExists(request.getEmail())) {
//            throw new IllegalArgumentException("Email already registered");
//        }
//        // Convert the role from String to Role enum
//        Role role = request.getRole();
//        var utilisateur = Utilisateur.builder()
//                .prenom(request.getPrenom())
//                .nom(request.getNom())
//                .email(request.getEmail())
//                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
//                .numTel(request.getNumTel())
//                .role(role)
//                .build();
//        var savedUser = utilisateurRepository.save(utilisateur);
//        var jwtToken = jwtService.generateToken(utilisateur);
//        var refreshToken = jwtService.generateRefreshToken(utilisateur);
//        saveUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//
//    public String generateToken(UserDetails userDetails) {
//        return jwtService.generateToken(userDetails);
//    }
//    public boolean userExists(String email) {
//        return utilisateurRepository.findByEmail(email).isPresent();
//    }
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        // Compare the entered password with the stored password
//        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
//            throw new BadCredentialsException("Invalid credentials");
//        }
//
//        String jwtToken = jwtService.generateToken(utilisateur);
//        String refreshToken = jwtService.generateRefreshToken(utilisateur);
//        revokeAllUserTokens(utilisateur);
//        saveUserToken(utilisateur, jwtToken);
//
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .prenom(utilisateur.getPrenom())
//                .nom(utilisateur.getNom())
//                .email(utilisateur.getEmail())
//                .NumTel(utilisateur.getNumTel())
//                .role(utilisateur.getRole().toString())
//                .build();
//    }
//
//
//
//    public boolean resetPassword(ChangePasswordRequest changePasswordRequest) {
//        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(changePasswordRequest.getEmail());
//        if (optionalUser.isEmpty()) {
//            return false;
//        }
//        Utilisateur utilisateur = optionalUser.get();
//        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), utilisateur.getPassword())) {
//            return false;
//        }
//        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())) {
//            return false;
//        }
//        utilisateur.setMotDePasse(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
//        utilisateurRepository.save(utilisateur);
//        return true;
//    }

    private void saveUserToken(AppUser user, String jwtToken) {
        var token = Token.builder()
                .appUser(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        System.out.println("Token saved: " + jwtToken);
    }


//    private void revokeAllUserTokens(Utilisateur utilisateur) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser((UUID) utilisateur.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//        System.out.println("All user tokens revoked for user: " + utilisateur.getEmail());
//    }



//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authorization header");
//            return;
//        }
//        final String refreshToken = authHeader.substring(7);
//        final String userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var utilisateur = utilisateurRepository.findByEmail(userEmail)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            if (jwtService.isTokenValid(refreshToken, utilisateur)) {
//                var accessToken = jwtService.generateToken(utilisateur);
//                revokeAllUserTokens(utilisateur);
//                saveUserToken(utilisateur, accessToken);
//                var authResponse = AuthenticationResponse.builder()
//                        .access_token(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            } else {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
//            }
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token extraction failed");
//        }
//    }

}
