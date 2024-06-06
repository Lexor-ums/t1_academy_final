package t1.limitapi.dto.response;

public record ErrorResponse(
        String code,
        String message
) {
}