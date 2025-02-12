package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.StatisticsDTO;
import com.zodus.questionize.dto.filters.StatisticsFilter;
import com.zodus.questionize.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {
  private final StatisticsService statisticsService;

  @GetMapping("/general")
  public ResponseEntity<StatisticsDTO> getStatistics(StatisticsFilter filter) {
    StatisticsDTO statistics = statisticsService.getStatistics(filter);

    return new ResponseEntity<>(statistics, HttpStatus.OK);
  }
}
