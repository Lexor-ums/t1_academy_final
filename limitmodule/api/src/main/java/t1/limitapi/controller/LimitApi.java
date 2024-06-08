package t1.limitapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import t1.limitapi.dto.request.LimitRequest;
import t1.limitapi.dto.response.LimitResponse;
import t1.limitapi.utils.UriConstants;

@RequestMapping(value = UriConstants.LIMIT)
public interface LimitApi {
    @PostMapping(UriConstants.DECREASE_LIMIT)
    LimitResponse decreaseLimit(@RequestBody LimitRequest request);

    @PostMapping(UriConstants.INCREASE_LIMIT)
    LimitResponse increaseLimit(@RequestBody LimitRequest request);
}
