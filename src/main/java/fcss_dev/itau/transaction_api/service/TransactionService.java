package fcss_dev.itau.transaction_api.service;

import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import fcss_dev.itau.transaction_api.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final List<TransactionRequestDTO> listTransaction = new ArrayList<>();

    public void addTransaction( TransactionRequestDTO dto){
        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            throw new UnprocessableEntity("");
        }
        if (dto.valor() < 0){
            throw new UnprocessableEntity("");
        }
        listTransaction.add(dto);
    }

    public void deleteTransaction(){
        listTransaction.clear();
    }

    public List<TransactionRequestDTO> searchTransaction(Integer intervaloBusca){
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        return listTransaction.stream()
                .filter(t -> t.dataHora()
                        .isAfter(dataHoraIntervalo))
                .toList();
    }

}
