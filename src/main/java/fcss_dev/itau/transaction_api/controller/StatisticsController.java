package fcss_dev.itau.transaction_api.controller;

import fcss_dev.itau.transaction_api.controller.dto.StatisticResponseDTO;
import fcss_dev.itau.transaction_api.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<StatisticResponseDTO> searchStatistics(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca){
        return ResponseEntity.ok(statisticService.calculateStatisticTransaction(intervaloBusca));
    }

}
