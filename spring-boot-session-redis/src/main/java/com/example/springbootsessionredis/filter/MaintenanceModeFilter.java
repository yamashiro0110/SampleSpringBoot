package com.example.springbootsessionredis.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Service Maintenance Mode Filter
 */
@WebFilter(urlPatterns = {"/info"})
@ConditionalOnResource(resources = "file:tmp/maintenance_mode.lock")
@Component
public class MaintenanceModeFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String message = this.maintenanceModeMessage();

    @Bean(name = "maintenanceModeMessage")
    String maintenanceModeMessage() {
        try {
            Path path = Paths.get("tmp", "maintenance_mode.lock");
            List<String> lines = Files.readAllLines(path);
            return StringUtils.join(lines, "\n");
        }
        catch (IOException e) {
            this.logger.debug("メンテナンスモードのメッセージ取得エラーです", e);
            return "現在メンテナンス中です";
        }
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        this.logger.debug("Enable Maintenance mode request URL:{}", request.getRequestURI());
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, this.message);
    }
}
