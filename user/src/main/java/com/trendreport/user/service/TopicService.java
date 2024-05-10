package com.trendreport.user.service;

import com.trendreport.user.model.Topic;
import com.trendreport.user.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    @Transactional
    public void createTopic(String topic){
        Topic interest = Topic.builder()
            .topic(topic).build();
        topicRepository.save(interest);
    }

}
