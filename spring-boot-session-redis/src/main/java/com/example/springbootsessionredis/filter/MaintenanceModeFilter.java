package com.example.springbootsessionredis.filter;

import com.example.springbootsessionredis.config.web.MaintenanceModeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Service Maintenance Mode Filter
 */
@WebFilter(urlPatterns = {"/*"})
@ConditionalOnProperty(name = "maintenance.mode.enable")
@Component
public class MaintenanceModeFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MaintenanceModeConfig maintenanceModeConfig;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        this.logger.debug("Enable Maintenance mode request URL:{}", request.getRequestURI());
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return this.maintenanceModeConfig.matchIgnoreMaintenanceRequest(path);
    }
}
