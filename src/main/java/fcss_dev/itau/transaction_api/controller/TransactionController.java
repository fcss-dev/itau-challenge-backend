package fcss_dev.itau.transaction_api.controller;

import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import fcss_dev.itau.transaction_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO dto){
        transactionService.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(){
        transactionService.deleteTransaction();
        return ResponseEntity.ok().build();
    }
}
