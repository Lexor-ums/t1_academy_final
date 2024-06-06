package t1.limitdatabasestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import t1.limitdatabasestarter.entity.Limit;

import java.math.BigDecimal;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    @Modifying
    @Query(value = "UPDATE limits SET unit_limit = ?1")
    void resetLimits(BigDecimal limit);
}
