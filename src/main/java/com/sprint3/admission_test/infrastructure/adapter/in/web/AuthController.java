package com.sprint3.admission_test.infrastructure.adapter.in.web;

import com.sprint3.admission_test.application.ports.in.IAuthorizationUseCase;
import com.sprint3.admission_test.domain.ports.out.UserDetailsImpl;
import com.sprint3.admission_test.infrastructure.adapter.in.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IAuthorizationUseCase authorizationUseCase;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody SecurityConfig.User user) {
        var autentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUser(),
                        user.getPassword()
                )
        );
        String jwtToken = authorizationUseCase.generateToken((UserDetailsImpl) autentication.getPrincipal());
        return ResponseEntity.ok(jwtToken);
    }

}
