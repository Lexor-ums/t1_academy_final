package t1.limitservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t1.limitapi.dto.request.LimitRequest;
import t1.limitservice.entity.Limit;

import java.math.BigDecimal;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    @Query(value = "insert into limits (id, user_limit) " +
            "  values (:#{#limit.clientId}, :defaultLimit) " +
            "  on conflict(id) do update set user_limit = limits.user_limit - :#{#limit.currentLimit} " +
            "  returning *", nativeQuery = true)
    Limit saveOrUpdateDescreased(BigDecimal defaultLimit, LimitRequest limit);

    @Query(value = "insert into limits (id, user_limit) " +
            "  values (:#{#limit.clientId}, :defaultLimit) " +
            "  on conflict(id) do update set user_limit = limits.user_limit + :#{#limit.currentLimit} " +
            "  returning *", nativeQuery = true)
    Limit saveOrUpdateIncreased(BigDecimal defaultLimit, LimitRequest limit);
}
