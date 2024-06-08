package t1.limitapi.dto.request;

import java.math.BigDecimal;

public record LimitRequest(
        Long clientId,
        BigDecimal currentLimit
) {
}
