package org.bissis.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Markus Ullrich
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("org.bissis")
public class JpaIntegrationConfig {

}
