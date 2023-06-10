package com.oguztasgin.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterRequestDto {
    private String email;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String birthDay;
    private String occupation;
}
