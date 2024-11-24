package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.GeneralStatisticsDTO;
import com.zodus.questionize.dto.filters.GeneralStatisticsFilter;
import com.zodus.questionize.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {
  private final StatisticsService statisticsService;

  @GetMapping("/general")
  public ResponseEntity<GeneralStatisticsDTO> getGeneralStatistics(GeneralStatisticsFilter filter) {
    GeneralStatisticsDTO statistics = statisticsService.getGeneralStatistics(filter);

    return new ResponseEntity<>(statistics, HttpStatus.OK);
  }

  @GetMapping("/questionary/{id}")
  public ResponseEntity<?> getStatisticsFromQuestionary(@PathVariable UUID id) {
    return null;
  }

  @GetMapping("/question/{id}")
  public ResponseEntity<?> getStatisticsFromQuestion(@PathVariable UUID id) {
    return null;
  }

  @GetMapping("/member/{id}")
  public ResponseEntity<?> getStatisticsFromMember(@PathVariable UUID id) {
    return null;
  }

  @GetMapping("/department/{id}")
  public ResponseEntity<?> getStatisticsFromDepartment(@PathVariable UUID id) {
    return null;
  }
}
