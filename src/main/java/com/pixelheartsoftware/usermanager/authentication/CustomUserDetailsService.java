package com.pixelheartsoftware.usermanager.authentication;

import com.pixelheartsoftware.usermanager.user.User;
import com.pixelheartsoftware.usermanager.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username:" + username));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getRoles(username)
        );
    }

    // For simplicity user roles are mocked
    private List<SimpleGrantedAuthority> getRoles(String username) {
        if ("admin".equals(username)) {
            return List.of(
                    new SimpleGrantedAuthority("USER"),
                    new SimpleGrantedAuthority("ADMIN"));
        }

        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }
}