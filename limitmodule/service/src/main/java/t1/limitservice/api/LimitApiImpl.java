package t1.limitservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import t1.limitapi.controller.LimitApi;
import t1.limitapi.dto.request.LimitRequest;
import t1.limitapi.dto.response.LimitResponse;
import t1.limitservice.service.LimitService;

@RestController
@RequiredArgsConstructor
public class LimitApiImpl implements LimitApi {
    private final LimitService limitService;

    @Override
    public LimitResponse decreaseLimit(LimitRequest request) {

        return limitService.decreaseLimit(request);
    }

    @Override
    public LimitResponse increaseLimit(LimitRequest request)
    {
        return limitService.increaseLimit(request);
    }
}
