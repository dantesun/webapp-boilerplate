package io.github.dantesun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dsun on 15/2/15.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ConfigurationProperties(prefix = "application")
class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (logger.isTraceEnabled()) {
            logger.trace("This name {} is set from application.properties", getName());
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
