package dev.vishesh.vaadiyancore.controller;

import dev.vishesh.vaadiyancore.dao.ChangePasswordRequest;
import dev.vishesh.vaadiyancore.dao.UserRequest;
import dev.vishesh.vaadiyancore.model.User;
import dev.vishesh.vaadiyancore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 * This class is used to handle the user related requests.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * This method is used to get the user by username.
     * @param username Username of the user
     * @return User
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    /**
     * This method is used to create a new user.
     * @param userRequest User details
     * @return User
     */
    @PutMapping( "/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUserByUsername(username, userRequest));
    }

    /**
     * This method is used to delete the user by username.
     * @param username Username of the user
     * @return Void
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok().build();
    }

    /**
     * This method is used to update the password by username.
     * @param username Username of the user
     * @param request ChangePasswordRequest
     * @return Void
     */
    @PatchMapping("/{username}")
    public ResponseEntity<Void> updatePassword(@PathVariable String username, @RequestBody ChangePasswordRequest request) {
        userService.updatePasswordByUsername(username, request);
        return ResponseEntity.ok().build();
    }
}