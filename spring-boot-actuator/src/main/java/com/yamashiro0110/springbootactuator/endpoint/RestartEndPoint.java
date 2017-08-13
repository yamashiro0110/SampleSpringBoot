package com.yamashiro0110.springbootactuator.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by yamashiro-r on 2017/08/13.
 */
@Component
public class RestartEndPoint implements Endpoint<RestartEndpointResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestartEndPoint.class);

    @Value("${sample.restart.scriptPath:/etc/init.d/spring-boot-session-redis}")
    private String scriptPath;

    @Override
    public String getId() {
        return "restart";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public RestartEndpointResponse invoke() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(this.scriptPath, "restart");
            Process process = processBuilder.start();
            LOGGER.info("restart application script:{}, process:{}", this.scriptPath, process.toString());
            return new RestartEndpointResponse("start restart");
        }
        catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return new RestartEndpointResponse(e.getMessage());
        }
    }
}
