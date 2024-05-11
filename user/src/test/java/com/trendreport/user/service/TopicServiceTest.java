package com.trendreport.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.trendreport.user.model.Topic;
import com.trendreport.user.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;
    @InjectMocks
    private TopicService topicService;

    @Test
    void createTopic() {
        //given
        ArgumentCaptor<Topic> captor = ArgumentCaptor.forClass(Topic.class);
        //when
        topicService.createTopic("topic");
        //then
        verify(topicRepository, times(1))
            .save(captor.capture());
        Topic topic = captor.getValue();
        assertEquals("topic",topic.getTopic());
    }
}