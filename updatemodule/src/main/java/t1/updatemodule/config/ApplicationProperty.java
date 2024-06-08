package t1.updatemodule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author YStepanov
 */
@ConfigurationProperties(prefix = "application")
public record ApplicationProperty(Integer maxLimit) {
}
