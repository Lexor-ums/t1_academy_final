package t1.limitservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t1.limitapi.dto.request.LimitRequest;
import t1.limitapi.dto.response.LimitResponse;
import t1.limitservice.entity.Limit;
import t1.limitservice.exception.LimitExhaustedException;
import t1.limitservice.props.ApplicationProperty;
import t1.limitservice.repository.LimitRepository;
import t1.limitservice.service.LimitService;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;
    private final ApplicationProperty applicationProperty;

    @Override
    @Transactional
    public LimitResponse decreaseLimit(LimitRequest request) {
        try {
            Limit lilimit = limitRepository.saveOrUpdateDescreased(applicationProperty.maxLimit(), request);
            return new LimitResponse(lilimit.getLimit());
        } catch (Exception exception) {
            throw new LimitExhaustedException(String.format("Превышен ежедневный лимит операций по продукту для клиента с ID %d", request.clientId()));
        }
    }

    @Override
    @Transactional
    public LimitResponse increaseLimit(LimitRequest request) {
        Limit lilimit = limitRepository.saveOrUpdateIncreased(applicationProperty.maxLimit(), request);
        return new LimitResponse(lilimit.getLimit());
    }

}
