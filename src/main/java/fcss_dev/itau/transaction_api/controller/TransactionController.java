package fcss_dev.itau.transaction_api.controller;

import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import fcss_dev.itau.transaction_api.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsável por adicionar transações ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação gravada com sucesso "),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição "),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ")
    })
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO dto){
        transactionService.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação deletadas com sucesso "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição "),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ")
    })
    public ResponseEntity<Void> deleteTransaction(){
        transactionService.deleteTransaction();
        return ResponseEntity.ok().build();
    }
}
