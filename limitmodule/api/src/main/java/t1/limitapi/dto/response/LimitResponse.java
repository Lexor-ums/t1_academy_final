package t1.limitapi.dto.response;

import java.math.BigDecimal;

public record LimitResponse(
        BigDecimal currentLimit
) {
}
