package com.pixelheartsoftware.usermanager.configuration;

import com.pixelheartsoftware.usermanager.user.User;
import com.pixelheartsoftware.usermanager.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class DataInitializerConfig implements ApplicationRunner {
    public static final String DEFAULT_PASSWORD = "qaz123";

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        repository.save(User.from(
                null,
                "admin",
                passwordEncoder.encode(DEFAULT_PASSWORD),
                "admin@reynholm.com",
                "Maurice",
                "Moss",
                LocalDate.of(1973, 2, 28)
        ));

        repository.save(User.from(
                null,
                "pietrek",
                passwordEncoder.encode(DEFAULT_PASSWORD),
                "some@pixelheartsoftware.com",
                "Piotr",
                "Kaczmarski",
                LocalDate.of(1987, 7, 27)
        ));

        repository.save(User.from(
                null,
                "johnny5",
                passwordEncoder.encode(DEFAULT_PASSWORD),
                "thejohn@cyberdyne.com",
                "John",
                "Connor",
                LocalDate.of(1985, 2, 28)
        ));

        repository.save(User.from(
                null,
                "kyle2003",
                passwordEncoder.encode(DEFAULT_PASSWORD),
                "kyle@cyberdyne.com",
                "Kyle",
                "Reese",
                LocalDate.of(2003, 11, 2)
        ));

        repository.save(User.from(
                null,
                "sconnor",
                passwordEncoder.encode(DEFAULT_PASSWORD),
                "sarah.connor@cyberdyne.com",
                "Sarah",
                "Connor",
                LocalDate.of(1965, 5, 11)
        ));
    }
}
