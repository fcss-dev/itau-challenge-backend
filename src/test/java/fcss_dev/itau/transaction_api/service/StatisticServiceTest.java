package fcss_dev.itau.transaction_api.service;

import fcss_dev.itau.transaction_api.controller.dto.StatisticResponseDTO;
import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {
    @InjectMocks
    StatisticService statisticService;

    @Mock
    TransactionService transactionService;

    TransactionRequestDTO transaction;
    StatisticResponseDTO statistics;

    @BeforeEach
    void setUp(){
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        statistics = new StatisticResponseDTO(1L, 20.0,20.0,20.0,20.0);
    }

    @Test
    void calculateStatisticsWithSucess(){
        when(transactionService.searchTransaction(60))
                .thenReturn(Collections.singletonList(transaction));
        StatisticResponseDTO result = statisticService.calculateStatisticTransaction(60);

        verify(transactionService, times(1)).searchTransaction(60);
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(statistics);
    }

    @Test
    void calculateStatisticsWhenListEmpty(){

        StatisticResponseDTO statisticExpected = new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);

        when(transactionService.searchTransaction(60))
                .thenReturn(Collections.emptyList());
        StatisticResponseDTO result = statisticService.calculateStatisticTransaction(60);

        verify(transactionService, times(1)).searchTransaction(60);
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(statisticExpected);
    }
}
