package com.pixelheartsoftware.usermanager.user;

import com.pixelheartsoftware.usermanager.user.exception.UserDataConflictException;
import com.pixelheartsoftware.usermanager.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        List<User> result = StreamSupport.stream(
                        userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.toUserDtoList(result);
    }

    public UserDto getUser(Integer userId) {
        return userRepository.findById(userId)
                .map(mapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException("No user with id " + userId));
    }

    public void removeUser(Integer userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("No user with id " + userId, e);
        }
    }

    public void addUser(UserDto user) {
        try {
            userRepository.save(mapper.toUser(user));
        } catch (DataIntegrityViolationException e) {
            throw new UserDataConflictException("Duplicated user data", e);
        }
    }

    public void updateUser(UserDto user, Integer userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user with id " + userId));

        foundUser.setUsername(user.getUsername());
        foundUser.setEmail(user.getEmail());
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setBirthDate(user.getBirthDate());

        String password = user.getPassword();
        if (password != null && !password.isBlank()) {
            foundUser.setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(foundUser);
    }
}
