package com.trendreport.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trendreport.user.dto.SignInForm;
import com.trendreport.user.dto.SignUpForm;
import com.trendreport.user.exception.CustomException;
import com.trendreport.user.exception.ErrorCode;
import com.trendreport.user.model.User;
import com.trendreport.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void signUp() {
        //given
        SignUpForm form = SignUpForm.builder()
            .email("111@naver.com")
            .password("1111")
            .sex(1)
            .age(22).build();
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        given(userRepository.existsByEmail(any()))
            .willReturn(false);
        given(passwordEncoder.encode(any()))
            .willReturn("encoded");
        //when
        userService.signUpUser(form);
        //then
        verify(userRepository, times(1))
            .save(captor.capture());
        User user = captor.getValue();
        assertEquals("111@naver.com",user.getEmail());
        assertEquals("encoded", user.getEncryptedPassword());
        assertEquals("W", user.getSex());
        assertEquals(22,user.getAge());
    }

    @DisplayName("회워가입 실패 - 가입된 이메일 존재")
    @Test
    void signUp_existEmail() {
        //given
        SignUpForm form = SignUpForm.builder()
            .email("111@naver.com")
            .password("1111")
            .sex(1)
            .age(22).build();
        given(userRepository.existsByEmail(any()))
            .willReturn(true);
        //when
        CustomException exception = assertThrows(CustomException.class,
            () -> userService.signUpUser(form));
        //then
        assertEquals(ErrorCode.ALREADY_REGISTERED_EMAIL.getMessage(), exception.getMessage());
    }

    @Test
    void authenticate() {
        //given
        SignInForm form = SignInForm.builder()
            .email("111@naver.com")
            .password("1111").build();
        User user = User.builder()
            .email("111@naver.com")
            .encryptedPassword("encryptedPassword")
            .isDeleted(false)
            .build();
        given(passwordEncoder.matches(any(),any()))
            .willReturn(true);
        given(userRepository.findUserByEmail(anyString()))
            .willReturn(Optional.ofNullable(user));
        //when
        String authenticate = userService.authenticate(form);
        //then
        assertEquals("로그인 성공",authenticate);
    }
    @DisplayName("로그인 실패 - 가입된 이메일 없음")
    @Test
    void authenticate_do_not_exist_email() {
        //given
        SignInForm form = SignInForm.builder()
            .email("111@naver.com")
            .password("1111").build();
        given(userRepository.findUserByEmail(anyString()))
            .willReturn(Optional.empty());
        //when
        CustomException exception = assertThrows(CustomException.class,
            () -> userService.authenticate(form));
        //then
        assertEquals(ErrorCode.DO_NOT_EXIST_EMAIL.getMessage(),exception.getMessage());
    }
    @DisplayName("로그인 실패 - 삭제된 계정")
    @Test
    void authenticate_deleted_account() {
        //given
        SignInForm form = SignInForm.builder()
            .email("111@naver.com")
            .password("1111").build();
        User user = User.builder()
            .email("111@naver.com")
            .encryptedPassword("encryptedPassword")
            .isDeleted(true)
            .build();
        given(userRepository.findUserByEmail(anyString()))
            .willReturn(Optional.ofNullable(user));
        //when
        CustomException exception = assertThrows(CustomException.class,
            () -> userService.authenticate(form));
        //then
        assertEquals(ErrorCode.DELETED_ACCOUNT.getMessage(),exception.getMessage());
    }
    @DisplayName("로그인 실패 - 비밀번호가 일치하지 않음")
    @Test
    void authenticate_mismatch_password() {
        //given
        SignInForm form = SignInForm.builder()
            .email("111@naver.com")
            .password("1111").build();
        User user = User.builder()
            .email("111@naver.com")
            .encryptedPassword("encryptedPassword")
            .isDeleted(false)
            .build();
        given(userRepository.findUserByEmail(anyString()))
            .willReturn(Optional.ofNullable(user));
        given(passwordEncoder.matches(any(),any()))
            .willReturn(false);
        //when
        CustomException exception = assertThrows(CustomException.class,
            () -> userService.authenticate(form));
        //then
        assertEquals(ErrorCode.DO_NOT_MATCH_PASSWORD.getMessage(),exception.getMessage());
    }
}