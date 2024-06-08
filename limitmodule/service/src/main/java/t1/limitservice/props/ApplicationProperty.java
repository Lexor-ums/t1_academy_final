package t1.limitservice.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * @author YStepanov
 */
@ConfigurationProperties(prefix = "application")
public record ApplicationProperty(BigDecimal maxLimit) {
}
