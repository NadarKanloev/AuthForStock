package com.Auth.Controller;

import com.Auth.DTO.JwtAuthenticationResponse;
import com.Auth.DTO.SignInRequest;
import com.Auth.DTO.SignUpRequest;
import com.Auth.Service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    AuthenticationService authenticationService;
    @InjectMocks
    AuthController authController;

    @Test
    void signUp_ReturnsOkAndJwtToken(){
        //given
        SignUpRequest request = new SignUpRequest("Nadar", "Nadar@mail.ru", "Password");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        when(authenticationService.signUp(request)).thenReturn(jwtAuthenticationResponse);
        //when
        var result =  authController.signUp(request);
        //then
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(jwtAuthenticationResponse, result.getBody());
    }
    @Test
    void signIn_ReturnsOkAndJwtToken(){
        //given
        SignInRequest signInRequest = new SignInRequest("Nadar", "Password");
        JwtAuthenticationResponse expected = new JwtAuthenticationResponse();
        when(authenticationService.signIn(signInRequest)).thenReturn(expected);
        //when
        var result = authController.signIn(signInRequest);
        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());
    }
}