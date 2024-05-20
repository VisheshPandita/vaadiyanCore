package dev.vishesh.vaadiyancore.service;

import dev.vishesh.vaadiyancore.dao.ChangePasswordRequest;
import dev.vishesh.vaadiyancore.dao.UserRequest;
import dev.vishesh.vaadiyancore.exception.CustomException;
import dev.vishesh.vaadiyancore.model.User;
import dev.vishesh.vaadiyancore.repository.TokenRepository;
import dev.vishesh.vaadiyancore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * UserService
 * This class is used to handle the user related requests.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public User findByUsername(String creatorUsername) {
        return userRepository.findByUsername(creatorUsername).orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND", 404));
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND", 404));
    }

    public User updateUserByUsername(String username, UserRequest userRequest) {
        User user = findByUsername(username);
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        User user = findByUsername(username);
        tokenRepository.deleteByUser(user);
        userRepository.delete(user);
    }

    public void updatePasswordByUsername(String username, ChangePasswordRequest request) {
        User user = findByUsername(username);
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new CustomException("Wrong password", "WRONG_PASSWORD", 400);
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new CustomException("Passwords are not the same", "PASSWORDS_NOT_SAME", 400);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
