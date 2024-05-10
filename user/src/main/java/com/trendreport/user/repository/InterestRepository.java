package com.trendreport.user.repository;

import com.trendreport.user.model.Interest;
import com.trendreport.user.model.Topic;
import com.trendreport.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findByUser(User user);
    Optional<Interest> findByUserAndTopic(User user, Topic topic);

}
