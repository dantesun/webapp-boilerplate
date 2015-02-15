package io.github.dantesun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by dsun on 15/2/15.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) throws Exception {
        logger.info("Starting my application ...");
        SpringApplication.run(Application.class, args);
    }
}
