package t1.updatemodule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import t1.limitdatabasestarter.repository.LimitRepository;
import t1.limitdatabasestarter.service.LimitDatabaseConfigurationService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class LimitResetTask {
    private final LimitRepository limitRepository;
    private final LimitDatabaseConfigurationService limitDatabaseConfigurationService;

    @Scheduled(cron = "${scheduler.cron-expression}")
    void resetLimits() {
        limitRepository.resetLimits(limitDatabaseConfigurationService.getMaxLimit());
    }
}
