package com.example.sampleswagger.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AsyncSupport {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void sleep() {
        try {
            this.logger.info("start sleep");
            TimeUnit.SECONDS.sleep(3);
            this.logger.info("end sleep");
        }
        catch (InterruptedException e) {
            this.logger.error(e.getMessage());
            throw new IllegalStateException(e);
        }
    }

}
