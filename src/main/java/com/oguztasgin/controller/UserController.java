package com.oguztasgin.controller;
import com.oguztasgin.dto.request.UserRegisterRequestDto;
import com.oguztasgin.dto.request.UserUpdateRequestDto;
import com.oguztasgin.repository.entity.User;
import com.oguztasgin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/register")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> register(@RequestBody @Valid UserRegisterRequestDto dto){
        //RequestBody'siz denenecek cunku @RestController var.
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/getbyuserid")
    @CrossOrigin("*")
    public ResponseEntity<User> getByUserId(Long userId){
        return ResponseEntity.ok(userService.getByUserId(userId));
    }

    @PutMapping("/updateuser")
    @CrossOrigin("*")
    public ResponseEntity<User> updateByUserId(@RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(userService.updateByUserId(dto));
    }

    @DeleteMapping("/deletebyuserid")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteByUserId(Long userId){
        return ResponseEntity.ok(userService.deleteByUserId(userId));
    }
}
