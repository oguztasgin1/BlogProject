package com.oguztasgin.controller;

import com.oguztasgin.dto.request.UserLoginRequestDto;
import com.oguztasgin.dto.response.DoLoginResponseDto;
import com.oguztasgin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;



    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> login(@RequestBody @Valid UserLoginRequestDto dto){
        return ResponseEntity.ok(
                DoLoginResponseDto.builder()
                        .token(userService.login(dto))
                        .build());
    }
}
