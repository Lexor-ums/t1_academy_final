package t1.limitservice.service;

import t1.limitapi.dto.request.LimitRequest;
import t1.limitapi.dto.response.LimitResponse;

public interface LimitService {
    LimitResponse decreaseLimit(LimitRequest request);

    LimitResponse increaseLimit(LimitRequest request);
}

