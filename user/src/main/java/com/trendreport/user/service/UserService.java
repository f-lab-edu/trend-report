package com.trendreport.user.service;

import com.trendreport.user.dto.Role;
import com.trendreport.user.dto.Sex;
import com.trendreport.user.dto.SignInForm;
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

    public String signUpUser(SignUpForm form){
        if(userRepository.existsByEmail(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_EMAIL);
        }else{
            User build = User.builder()
                .email(form.getEmail())
                .encryptedPassword(passwordEncoder.encode(form.getPassword()))
                .sex(form.getSex() == 1 ? Sex.WOMAN.getDescription() : Sex.MAN.getDescription())
                .age(form.getAge())
                .role(Role.ROLE_USER)
                .isDeleted(false)
                .build();
            userRepository.save(build);
            return "회원가입 완료";
        }
    }

    public String authenticate(SignInForm form){
        User user = userRepository.findByEmail(form.getEmail())
            .orElseThrow(() -> new CustomException(ErrorCode.DO_NOT_EXIST_EMAIL));
        if(user.isDeleted()){
            throw new CustomException(ErrorCode.DELETED_ACCOUNT);
        }
        if(!passwordEncoder.matches(form.getPassword(),user.getEncryptedPassword())){
            throw new CustomException(ErrorCode.DO_NOT_MATCH_PASSWORD);
        }
        return "로그인 성공";
    }
}
