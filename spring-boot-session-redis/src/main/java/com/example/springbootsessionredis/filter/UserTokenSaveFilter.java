package com.example.springbootsessionredis.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yamashiro-r on 2017/05/28.
 */
@Component
public class UserTokenSaveFilter extends GenericFilterBean {
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String token = request.getParameter("token");

        if (StringUtils.isNotBlank(token)) {
            HttpSession session = servletRequest.getSession();
            session.setAttribute("userToken", token);
        }

        chain.doFilter(request, response);
    }
}
