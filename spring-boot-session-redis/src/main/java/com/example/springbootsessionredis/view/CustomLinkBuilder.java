package com.example.springbootsessionredis.view;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.linkbuilder.AbstractLinkBuilder;
import org.thymeleaf.linkbuilder.StandardLinkBuilder;

import java.util.Map;

/**
 * Created by yamashiro-r on 2017/06/17.
 */
@Component
public class CustomLinkBuilder extends AbstractLinkBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLinkBuilder.class);
    private final StandardLinkBuilder standardLinkBuilder = new StandardLinkBuilder();

    private String cdnHost() {
        return "http://localhost:8080";
    }

    private boolean isImageExtention(String path) {
        return StringUtils.endsWith(path, ".jpg")
                || StringUtils.endsWith(path, ".jpeg")
                || StringUtils.endsWith(path, ".png")
                || StringUtils.endsWith(path, "gif");
    }

    private boolean isImageLink(UriComponents components) {
        final String path = components.getPath();
        return StringUtils.startsWith(path, "/image/") && this.isImageExtention(path);
    }

    public String buildLink(String link) {
        if (UrlUtils.isAbsoluteUrl(link)) {
            return link;
        }

        UriComponents components = UriComponentsBuilder.fromUriString(link).build();

        if (this.isImageLink(components)) {
            return this.cdnHost() + link;
        }

        return link;
    }

    @Override
    public String buildLink(final IExpressionContext context, final String base, final Map<String, Object> parameters) {
        String link = this.standardLinkBuilder.buildLink(context, base, parameters);
        LOGGER.debug("build link:{}", link);
        return this.buildLink(link);
    }
}
