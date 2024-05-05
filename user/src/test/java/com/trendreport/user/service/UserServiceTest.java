package com.trendreport.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trendreport.user.dto.SignUpForm;
import com.trendreport.user.exception.CustomException;
import com.trendreport.user.exception.ErrorCode;
import com.trendreport.user.model.User;
import com.trendreport.user.repository.UserRepository;
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
        userService.signUp(form);
        //then
        verify(userRepository, times(1))
            .save(captor.capture());
        User user = captor.getValue();
        assertEquals("111@naver.com",user.getEmail());
        assertEquals("encoded", user.getPassword());
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
            () -> userService.signUp(form));
        //then
        assertEquals(ErrorCode.ALREADY_REGISTER_EMAIL.getMessage(), exception.getMessage());
    }
}