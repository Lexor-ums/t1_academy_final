package t1.limitdatabasestarter.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t1.limitdatabasestarter.entity.Configuration;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames = {"limitDatabase"})
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    @Cacheable
    @Query(value = "SELECT c FROM Configuration c WHERE name =:name ")
    Optional<Configuration> getPropertyByName(String name);
}
