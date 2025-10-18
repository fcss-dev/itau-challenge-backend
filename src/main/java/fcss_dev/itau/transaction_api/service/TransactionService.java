package fcss_dev.itau.transaction_api.service;

import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import fcss_dev.itau.transaction_api.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final List<TransactionRequestDTO> listTransaction = new ArrayList<>();

    public void addTransaction( TransactionRequestDTO dto){
        log.info("Iniciado o processamento de gravar transações!" + dto);
        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity(" ");
        }
        if (dto.valor() < 0){
            log.error("Valor não pode ser menor que 0!");
            throw new UnprocessableEntity(" ");
        }

        listTransaction.add(dto);
        log.info("Transações adicionadas com sucesso");
    }

    public void deleteTransaction(){
        log.info("Iniciado o processamento para deletar transações");
        listTransaction.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransactionRequestDTO> searchTransaction(Integer intervaloBusca){
        log.info("Iniciada buscas de Transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listTransaction.stream()
                .filter(t -> t.dataHora()
                        .isAfter(dataHoraIntervalo))
                .toList();
    }

}
