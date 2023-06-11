package com.oguztasgin.service;

import com.oguztasgin.dto.request.UserLoginRequestDto;
import com.oguztasgin.dto.request.UserRegisterRequestDto;
import com.oguztasgin.dto.request.UserUpdateRequestDto;
import com.oguztasgin.dto.response.DoLoginResponseDto;
import com.oguztasgin.repository.IUserRepository;
import com.oguztasgin.repository.entity.User;
import com.oguztasgin.security.JwtTokenManager;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository repository;
    @Autowired
    private JwtTokenManager jwtTokenManager;

    public UserService(IUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Boolean register(UserRegisterRequestDto dto) {
        User user = User.builder()
                .password(dto.getPassword())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .name(dto.getName())
                .surname(dto.getSurname())
                .birthDay(dto.getBirthDay())
                .occupation(dto.getOccupation())
                .build();
        save(user);
        return true;
    }

    public User getByUserId(Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isEmpty()){
            System.out.println("Kullanici bulunamadi");
        }
        return user.get();
    }

    public User updateByUserId(UserUpdateRequestDto dto) {
        Optional<User> user = repository.findById(dto.getUserId());
        if (user.isEmpty()){
            System.out.println("Kullanici bulunamadi");
        }
        user.get().setUsername(dto.getUsername());
        user.get().setPassword(dto.getPassword());
        user.get().setOccupation(dto.getOccupation());
        save(user.get());
        return user.get();
    }

    public Boolean deleteByUserId(Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isEmpty()){
            System.out.println("Kullanici bulunamadi");
            return false;
        }
        delete(user.get());
        return true;
    }

    public String login(UserLoginRequestDto dto) {
        Optional<User> user = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isEmpty()){
            System.out.println("Kullanıcı bulunamadi");
            return null;
        }
        Optional<String> token = jwtTokenManager.createToken(user.get().getId());
        if (user.isEmpty()){
            System.out.println("Token bulunamadi");
            return null;
        }
        return token.get();
    }
}
