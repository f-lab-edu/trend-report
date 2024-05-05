package com.trendreport.user.service;

import com.trendreport.user.dto.Sex;
import com.trendreport.user.dto.SignUpForm;
import com.trendreport.user.exception.CustomException;
import com.trendreport.user.exception.ErrorCode;
import com.trendreport.user.model.User;
import com.trendreport.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signUp(SignUpForm form){
        if(userRepository.existsByEmail(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_EMAIL);
        }else{
            User build = User.builder()
                .email(form.getEmail())
                .password(this.passwordEncoder.encode(form.getPassword()))
                .sex(form.getSex() == 1 ? Sex.WOMAN.getDescription() : Sex.MAN.getDescription())
                .age(form.getAge()).build();
            User user = userRepository.save(build);
            return "회원가입 완료";
        }
    }
}
