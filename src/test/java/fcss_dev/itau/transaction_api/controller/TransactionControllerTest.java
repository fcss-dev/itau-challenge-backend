package fcss_dev.itau.transaction_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fcss_dev.itau.transaction_api.controller.dto.TransactionRequestDTO;
import fcss_dev.itau.transaction_api.exceptions.UnprocessableEntity;
import fcss_dev.itau.transaction_api.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.OffsetDateTime;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    TransactionRequestDTO transaction;

    MockMvc mockMvc;

    @Autowired
    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(){
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.of(2025, 2, 18, 14, 30, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void mustAddSuccessfulTransaction() throws Exception {

        doNothing().when(transactionService).addTransaction(transaction);

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGenerateExceptionWhenAddingTransaction() throws Exception {
        doThrow(new UnprocessableEntity("Erro de requisição")).when(transactionService).addTransaction(transaction);
        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void MustDeleteTransactionsSuccessfully() throws Exception {
        doNothing().when(transactionService).deleteTransaction();
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }

}
