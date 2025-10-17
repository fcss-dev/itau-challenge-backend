package fcss_dev.itau.transaction_api.controller.dto;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dataHora) {}
