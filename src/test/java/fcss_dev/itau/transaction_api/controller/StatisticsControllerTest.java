package fcss_dev.itau.transaction_api.controller;

import fcss_dev.itau.transaction_api.controller.dto.StatisticResponseDTO;
import fcss_dev.itau.transaction_api.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {
    @InjectMocks
    StatisticsController statisticsController;

    @Mock
    StatisticService statisticService;

    StatisticResponseDTO statistics;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();

        statistics = new StatisticResponseDTO(1L, 20.0, 20.0, 20.0,20.0);
    }

    @Test
    void MustSearchStatisticsComSucesso() throws Exception {

        when(statisticService.calculateStatisticTransaction(60)).thenReturn(statistics);

        mockMvc.perform(get("/estatistica")
                        .param("intervaloBusca", "60")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(statistics.count()));

    }
}
