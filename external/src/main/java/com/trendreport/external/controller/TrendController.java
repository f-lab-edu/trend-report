package com.trendreport.external.controller;

import com.trendreport.external.dto.TrendReportForm;
import com.trendreport.external.dto.TrendResponse;
import com.trendreport.external.service.ReportService;
import com.trendreport.external.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/external")
public class TrendController {

    private final TrendService trendService;
    private final ReportService reportService;

    @PostMapping()
    public ResponseEntity<?> getTrendReport(@RequestBody TrendReportForm form){
        final TrendResponse response = trendService.getSearchTrend(trendService.createTrendDto(form));
        return ResponseEntity.ok(reportService.getReport(response));
    }
}
