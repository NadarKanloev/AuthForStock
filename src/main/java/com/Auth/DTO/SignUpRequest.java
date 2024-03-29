package com.Auth.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description =  "Запрос на регистрацию")
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "Nadar")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "имя пользователя не должно быть пустым")
    private String username;

    @Schema(description = "Адрес электронной почты", example = "donat290817@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255")
    @NotBlank(message = "Поле адреса электронной почты не должно быть пустым")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "jfsvdckzxl")
    @Size(max = 255, message = "Длина пароль не должна превышать 255 символов")
    private String password;
}
