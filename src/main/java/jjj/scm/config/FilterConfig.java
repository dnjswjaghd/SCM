package jjj.scm.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final Filter filter;

    public FilterConfig(@Qualifier("customLoggingFilter") Filter filter){
        this.filter = filter;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(filter);
        filterFilterRegistrationBean.setEnabled(false);

        return filterFilterRegistrationBean;
    }
}
