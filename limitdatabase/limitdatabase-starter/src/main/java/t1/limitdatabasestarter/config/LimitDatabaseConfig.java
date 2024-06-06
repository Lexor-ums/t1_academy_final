package t1.limitdatabasestarter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("t1.limitdatabasestarter")
@EnableJpaRepositories("t1.limitdatabasestarter.repository")
@EntityScan("t1.limitdatabasestarter.entity")
public class LimitDatabaseConfig {
    public LimitDatabaseConfig() {

    }
}
