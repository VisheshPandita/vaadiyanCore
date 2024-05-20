package dev.vishesh.vaadiyancore.repository;

import dev.vishesh.vaadiyancore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository
 * This class is used to handle the database operations related to the user.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

}