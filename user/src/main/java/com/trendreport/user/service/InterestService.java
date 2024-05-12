package com.trendreport.user.service;

import com.trendreport.user.dto.InterestDto;
import com.trendreport.user.exception.CustomException;
import com.trendreport.user.exception.ErrorCode;
import com.trendreport.user.model.Interest;
import com.trendreport.user.model.Topic;
import com.trendreport.user.model.User;
import com.trendreport.user.repository.InterestRepository;
import com.trendreport.user.repository.TopicRepository;
import com.trendreport.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final TopicService topicService;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final InterestRepository interestRepository;

    @Transactional
    public boolean addInterest(Long userId, String title){
        if(!topicRepository.existsByName(title)){
            topicService.createTopic(title);
        }

        Topic topic = topicRepository.findByName(title)
            .orElseThrow(() -> new CustomException(ErrorCode.DO_NOT_MATCH_TOPIC));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.DO_NOT_EXIST_USER));
        List<Interest> interestList = interestRepository.findByUser(user);

        if(interestList.size() > 3){
            throw new CustomException(ErrorCode.ALREADY_FULL_INTEREST);
        }

        for(Interest i : interestList){
            if(i.getTopic() == topic){
                throw  new CustomException(ErrorCode.EXIST_INTEREST);
            }
        }

        Interest interest = Interest.builder()
            .user(user)
            .topic(topic).build();

        interestRepository.save(interest);
        return true;
    }
    public List<InterestDto> getInterest(Long userId){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.DO_NOT_EXIST_USER));

        return interestRepository.findByUser(user).stream()
            .map(i -> InterestDto.builder()
                .id(i.getId())
                .topic(i.getTopic().getName())
                .build())
            .collect(Collectors.toList());
    }
    @Transactional
    public boolean deleteInterest(Long userId, Long topicId){

        Interest interest = interestRepository.findByUser_IdAndTopic_Id(userId, topicId)
            .orElseThrow(() -> new CustomException(ErrorCode.DO_NOT_EXIST_INTEREST));

        interestRepository.delete(interest);
        return true;
    }
}
