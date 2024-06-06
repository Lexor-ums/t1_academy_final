package t1.limitdatabasestarter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import t1.limitdatabasestarter.entity.Configuration;
import t1.limitdatabasestarter.repository.ConfigurationRepository;

import java.math.BigDecimal;

@Service
public class LimitDatabaseConfigurationService {
    public LimitDatabaseConfigurationService (ConfigurationRepository configurationRepository) {
        this.repository = configurationRepository;
    }
    private final ConfigurationRepository repository;

    private static final String MAX_LIMIT_PROPERTY_NAME = "max_limit";
    private static final String MAX_LIMIT_DEFAULT = "10000.0";

    public BigDecimal getMaxLimit() {
        String value = repository.getPropertyByName(MAX_LIMIT_PROPERTY_NAME).orElse(new Configuration(null, null, MAX_LIMIT_DEFAULT)).getValue();
        return NumberUtils.parseNumber(value, BigDecimal.class);
    }

    @CacheEvict(value = "limitDatabase", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.hotelListTTL}")
    public void cleanCache(){}
}
