package com.oguztasgin.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterRequestDto {
    @Email(message = "Lütfen geçerli bir e-mail adresi giriniz.")
    private String email;
    @NotBlank
    @Size(min=3,max = 20 ,message = "Kullanici adi en az 3 karakter en fazla 20 karakter olabilir")
    private String username;
    @NotNull(message = "Şifre boş geçilemez.")
    @Size(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifre enaz 8 Karakter, Enaz bir büyük bir küçük harf, Sayı ve özel karakterden oluşmalıdır.")
    private String password;
    @NotNull(message = "Şifre boş geçilemez.")
    @Size(min = 8, max = 64)
    String repassword;
    private String name;
    private String surname;
    private String birthDay;
    private String occupation;
}
