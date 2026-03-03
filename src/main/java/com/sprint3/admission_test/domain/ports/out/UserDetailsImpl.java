package com.sprint3.admission_test.domain.ports.out;

import com.sprint3.admission_test.infrastructure.adapter.in.security.SecurityConfig;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private List<String> roles;

    public static UserDetailsImpl of(SecurityConfig.User user) {
        return UserDetailsImpl.builder()
                .password(user.getPassword())
                .username(user.getUser())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
