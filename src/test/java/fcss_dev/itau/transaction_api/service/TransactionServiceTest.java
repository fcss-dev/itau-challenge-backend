package fcss_dev.itau.transaction_api.service;

import fcss_dev.itau.transaction_api.controller.dto.StatisticResponseDTO;
import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import fcss_dev.itau.transaction_api.exceptions.UnprocessableEntity;
import jakarta.validation.constraints.AssertTrue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @InjectMocks
    TransactionService transactionService;


    TransactionRequestDTO transaction;
    StatisticResponseDTO statistics;

    @BeforeEach
    void setUp(){
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.now());
    }

    @Test
    void shouldAddTransactionWithSuccess(){
        transactionService.addTransaction(transaction);

        List<TransactionRequestDTO> transactions = transactionService.searchTransaction(60);

        assertTrue(transactions.contains(transaction));
    }


    @Test
    void shouldThrowExceptionValueNegative(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> transactionService.addTransaction(new TransactionRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals(" ", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionDateTimeGreaterCurrent(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> transactionService.addTransaction(new TransactionRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals(" ", exception.getMessage());
    }

    @Test
    void shouldClearTransactionWithSuccess(){
        transactionService.deleteTransaction();

        List<TransactionRequestDTO> transactions = transactionService.searchTransaction(60);

        assertTrue(transactions.isEmpty());
    }

    @Test
    void shouldSearchTransactionsWithinRange(){
        TransactionRequestDTO dto = new TransactionRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transactionService.addTransaction(transaction);
        transactionService.addTransaction(dto);

        List<TransactionRequestDTO> transactions = transactionService.searchTransaction(60);

        assertTrue(transactions.contains(transaction));
        assertFalse(transactions.contains(dto));
    }



}
