package com.trendreport.user.controller;

import com.trendreport.user.dto.InterestDto;
import com.trendreport.user.dto.SecurityUser;
import com.trendreport.user.service.InterestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/interests")
@Slf4j
public class InterestController {

    private final InterestService interestService;

    @PutMapping()
    public ResponseEntity<Boolean> addInterest(@AuthenticationPrincipal SecurityUser user
        , @RequestParam("topic") String topic){
        return ResponseEntity.ok(interestService.addInterest(user.getUser().getId(), topic));
    }
    @GetMapping()
    public ResponseEntity<List<InterestDto>> getInterest(@AuthenticationPrincipal SecurityUser user){
        return ResponseEntity.ok(interestService.getInterest(user.getUser().getId()));
    }
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteInterest(@AuthenticationPrincipal SecurityUser user
        , @RequestParam("topic") Long topic){
        return ResponseEntity.ok(interestService.deleteInterest(user.getUser().getId(), topic));
    }

}
