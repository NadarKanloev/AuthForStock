package com.Auth.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema
public class SignInRequest {
    @Schema(description = "Имя пользователя", example = "Nadar")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Schema(description = "Пароль", example = "jdfvmdfmkv")
    @Size(min = 8, max = 255, message = "Пароль должен быть от 5 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
