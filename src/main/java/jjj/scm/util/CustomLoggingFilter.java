package jjj.scm.util;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("customLoggingFilter")
public class CustomLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Custom logging filter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
