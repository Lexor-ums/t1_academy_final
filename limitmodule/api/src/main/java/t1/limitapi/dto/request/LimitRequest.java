package t1.limitapi.dto.request;

import java.math.BigDecimal;

public record LimitRequest(
        String clientId,
        BigDecimal currentLimit
) {
}
