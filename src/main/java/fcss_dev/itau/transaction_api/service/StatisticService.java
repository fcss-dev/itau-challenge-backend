package fcss_dev.itau.transaction_api.service;


import fcss_dev.itau.transaction_api.controller.dto.StatisticResponseDTO;
import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    public final TransactionService transactionService;

    public StatisticResponseDTO calculateStatisticTransaction(Integer intervaloBusca){
        List<TransactionRequestDTO> transactions = transactionService.searchTransaction(intervaloBusca);

        if (transactions.isEmpty()){
            return new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics StatisticsTransactions = transactions.stream()
                .mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        return new StatisticResponseDTO(StatisticsTransactions.getCount(),
                StatisticsTransactions.getSum(),
                StatisticsTransactions.getAverage(),
                StatisticsTransactions.getMin(),
                StatisticsTransactions.getMax());
    }
}
