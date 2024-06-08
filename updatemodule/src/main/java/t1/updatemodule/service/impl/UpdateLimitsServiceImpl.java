package t1.updatemodule.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t1.updatemodule.config.ApplicationProperty;
import t1.updatemodule.service.UpdateLimitsService;

/**
 * @author YStepanov
 */
@Service
@RequiredArgsConstructor
public class UpdateLimitsServiceImpl implements UpdateLimitsService {
    private final EntityManager entityManager;
    private final ApplicationProperty applicationProperty;

    @Override
    public void updateLimits() {
        entityManager.createQuery("UPDATE limits SET unit_limit = :newLimit")
                .setParameter("newLimit", applicationProperty.maxLimit())
                .executeUpdate();
    }
}
