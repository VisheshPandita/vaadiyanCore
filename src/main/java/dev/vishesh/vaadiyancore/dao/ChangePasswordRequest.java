package dev.vishesh.vaadiyancore.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * ChangePasswordRequest
 * This class is used to store the change password request details.
 */
@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}