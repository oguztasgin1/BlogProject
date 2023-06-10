package com.oguztasgin.service;

import com.oguztasgin.dto.request.UserRegisterRequestDto;
import com.oguztasgin.dto.request.UserUpdateRequestDto;
import com.oguztasgin.repository.IUserRepository;
import com.oguztasgin.repository.entity.User;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository repository;

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
}
