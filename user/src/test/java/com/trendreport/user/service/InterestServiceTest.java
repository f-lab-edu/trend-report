package com.trendreport.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.trendreport.user.dto.InterestDto;
import com.trendreport.user.exception.CustomException;
import com.trendreport.user.exception.ErrorCode;
import com.trendreport.user.model.Interest;
import com.trendreport.user.model.Topic;
import com.trendreport.user.model.User;
import com.trendreport.user.repository.InterestRepository;
import com.trendreport.user.repository.TopicRepository;
import com.trendreport.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InterestServiceTest {
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private InterestRepository interestRepository;
    @InjectMocks
    private InterestService interestService;

    private Topic topic = Topic.builder()
        .topic("topic").build();
    private User user = User.builder()
        .id(1L).build();
    private List<Interest> interestList = new ArrayList<>();
    private Interest interest = Interest.builder()
        .user(user)
        .topic(topic).build();

    @Test
    void addInterest() {
        //given
        given(topicRepository.existsByTopic(any()))
            .willReturn(true);
        given(topicRepository.findByTopic(any()))
            .willReturn(Optional.ofNullable(topic));
        given(userRepository.findById(any()))
            .willReturn(Optional.ofNullable(user));
        given(interestRepository.findByUser(any()))
            .willReturn(interestList);
        ArgumentCaptor<Interest> captor = ArgumentCaptor.forClass(Interest.class);
        //when
        interestService.addInterest(1L,"topic");
        //then
        verify(interestRepository, times(1))
            .save(captor.capture());
        Interest captorValue = captor.getValue();
        assertEquals("topic",captorValue.getTopic().getTopic());
        assertEquals(1L,captorValue.getUser().getId());
    }
    @DisplayName("관심사 추가 실패 - 관심사 항목 최대")
    @Test
    void addInterest_fullInterest() {
        //given
        interestList.add(interest);
        interestList.add(interest);
        interestList.add(interest);
        interestList.add(interest);
        given(topicRepository.existsByTopic(any()))
            .willReturn(true);
        given(topicRepository.findByTopic(any()))
            .willReturn(Optional.ofNullable(topic));
        given(userRepository.findById(any()))
            .willReturn(Optional.ofNullable(user));
        given(interestRepository.findByUser(any()))
            .willReturn(interestList);
        //when
        CustomException exception = assertThrows(CustomException.class,
            () -> interestService.addInterest(1L, "topic"));
        //then
        assertEquals(ErrorCode.ALREADY_FULL_INTEREST.getMessage(),exception.getMessage());
    }
    @DisplayName("관심사 추가 실패 - 이미 추가한 관심사")
    @Test
    void addInterest_alreadyAdd() {
        //given
        interestList.add(interest);
        given(topicRepository.existsByTopic(any()))
            .willReturn(true);
        given(topicRepository.findByTopic(any()))
            .willReturn(Optional.ofNullable(topic));
        given(userRepository.findById(any()))
            .willReturn(Optional.ofNullable(user));
        given(interestRepository.findByUser(any()))
            .willReturn(interestList);
        //when
        CustomException exception = assertThrows(CustomException.class,
            () -> interestService.addInterest(1L, "topic"));
        //then
        assertEquals(ErrorCode.EXIST_INTEREST.getMessage(),exception.getMessage());
    }

    @Test
    void getInterest() {
        //given
        interestList.add(interest);
        given(userRepository.findById(any()))
            .willReturn(Optional.ofNullable(user));
        given(interestRepository.findByUser(any()))
            .willReturn(interestList);
        //when
        List<InterestDto> dtoList = interestService.getInterest(1L);
        //then
        assertThat(dtoList).hasSize(1);
        assertThat(dtoList).first().isEqualTo(InterestDto.builder().topic(topic.getTopic()).build());
    }

    @Test
    void deleteInterest() {
        //given
        given(userRepository.findById(any()))
            .willReturn(Optional.ofNullable(user));
        given(topicRepository.findByTopic(any()))
            .willReturn(Optional.ofNullable(topic));
        given(interestRepository.findByUserAndTopic(any(),any()))
            .willReturn(Optional.ofNullable(interest));
        //when
        interestService.deleteInterest(1L,"topic");
        //then
        verify(interestRepository, times(1)).delete(any(Interest.class));
    }
}