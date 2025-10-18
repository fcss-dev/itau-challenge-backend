package fcss_dev.itau.transaction_api.service;


import fcss_dev.itau.transaction_api.controller.dto.StatisticResponseDTO;
import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    public final TransactionService transactionService;

    public StatisticResponseDTO calculateStatisticTransaction(Integer intervaloBusca){
        log.info("Iniciada busca de estatísticas de transações pelo período de tempo" + intervaloBusca);

        long start = System.nanoTime();

        List<TransactionRequestDTO> transactions = transactionService.searchTransaction(intervaloBusca);

        if (transactions.isEmpty()){
            return new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics StatisticsTransactions = transactions.stream()
                .mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        long finish = System.nanoTime();
        long timeRequest = finish - start;
        double timeRequestMs = timeRequest / 1_000_000.0;

        System.out.println("Tempo de requisição: " + timeRequestMs + " milissengundos");


        log.info("Estatísticas retornadas com sucesso");
        return new StatisticResponseDTO(StatisticsTransactions.getCount(),
                StatisticsTransactions.getSum(),
                StatisticsTransactions.getAverage(),
                StatisticsTransactions.getMin(),
                StatisticsTransactions.getMax());
    }
}
