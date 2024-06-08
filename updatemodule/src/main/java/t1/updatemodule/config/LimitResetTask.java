package t1.updatemodule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import t1.updatemodule.service.UpdateLimitsService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class LimitResetTask {
    private final UpdateLimitsService updateLimitsService;

    @Scheduled(cron = "${scheduler.cron-expression}")
    void resetLimits() {
        updateLimitsService.updateLimits();
    }
}
