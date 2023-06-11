package com.oguztasgin.dto.request;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserLoginRequestDto {
    @NotBlank
    @Size(min=3,max = 20)
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
}
