package com.coudevi.security;

import com.coudevi.domain.model.UserEntity;
import com.coudevi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario : " + username + " no existe"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                        .collect(Collectors.toList()))
                .build();
    }
}
