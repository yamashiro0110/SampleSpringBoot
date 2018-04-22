package com.example.springbootsessionredis.config.web;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "maintenance.mode")
@Data
public class MaintenanceModeConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean enable = false;
    private String messageFilePath = "tmp/maintenance_mode.txt";
    private List<String> ignorePaths = Arrays.asList("/image/", "/css/", "/js/", "/favicon.ico");
    @Setter(AccessLevel.NONE)
    @Resource(name = "maintenanceModeMessage")
    private String message;

    public String getMessage() {
        return message;
    }

    @Bean("maintenanceModeMessage")
    String readMaintenanceModeMessage() {
        try {
            Path path = Paths.get(this.messageFilePath);

            if (!Files.exists(path)) {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            List<String> lines = Files.lines(path).collect(Collectors.toList());
            return StringUtils.join(lines, System.lineSeparator());
        }
        catch (IOException e) {
            this.logger.warn("メンテナンスモードのメッセージ取得でエラーが発生しました", e);
            return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
        }
    }

    public boolean matchIgnoreMaintenanceRequest(String path) {
        return this.ignorePaths.stream().anyMatch(it -> StringUtils.startsWith(path, it));
    }

}
