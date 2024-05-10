package com.trendreport.user.repository;

import com.trendreport.user.model.Topic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTopic(String topic);
    Optional<Topic> findByTopic(String topic);
}
