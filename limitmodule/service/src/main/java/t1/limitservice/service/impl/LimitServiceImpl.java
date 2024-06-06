package t1.limitservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t1.limitapi.dto.request.LimitRequest;
import t1.limitapi.dto.response.LimitResponse;
import t1.limitdatabasestarter.entity.Limit;
import t1.limitdatabasestarter.repository.LimitRepository;
import t1.limitdatabasestarter.service.LimitDatabaseConfigurationService;
import t1.limitservice.exception.LimitExhaustedException;
import t1.limitservice.service.LimitService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {
    private final LimitDatabaseConfigurationService limitDatabaseConfigurationService;
    private final LimitRepository limitRepository;

    @Override
    @Transactional
    public LimitResponse decreaseLimit(LimitRequest request) {

        Long clientId = Long.valueOf(request.clientId());
        BigDecimal value = request.currentLimit();
        var limit = limitRepository.findById(clientId)
                .orElseGet(() -> createLimitForNewUser(clientId));

        BigDecimal newLimit = limit.getLimit().subtract(value);
        if (newLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new LimitExhaustedException(String.format("Превышен ежедневный лимит операций по продукту для клиента с ID %d", clientId));
        }

        limit.setLimit(newLimit);
        limitRepository.save(limit);
        return new LimitResponse(newLimit);
    }

    @Override
    @Transactional
    public LimitResponse increaseLimit(LimitRequest request) {
        Long clientId = Long.valueOf(request.clientId());
        BigDecimal value = request.currentLimit();
        var limit = limitRepository.findById(clientId)
                .map(limit1 -> {
                    limit1.setLimit(limit1.getLimit().add(value));
                    return limit1;})
                .orElseGet(() -> createLimitForNewUser(clientId));

        limitRepository.save(limit);
        return new LimitResponse(limit.getLimit());
    }

    private Limit createLimitForNewUser(Long userId) {
        Limit newLimit = new Limit(null, limitDatabaseConfigurationService.getMaxLimit());
        return limitRepository.save(newLimit);
    }
}
