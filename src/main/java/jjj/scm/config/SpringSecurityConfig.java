package jjj.scm.config;

import jakarta.servlet.Filter;
import jjj.scm.util.CustomJwtFilter;
import jjj.scm.util.CustomLoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final String[] PASS_URL_LIST = List.of("/**").toArray(new String[0]); //, "/test/**", "/login", "/swagger-ui", "swagger-ui.html", "swagger-ui/**", "/v3/api-docs/**"
    private final OncePerRequestFilter oncePerRequestFilter;
    private final Filter loggingFilter;

    public SpringSecurityConfig(@Qualifier("customJwtFilter") OncePerRequestFilter oncePerRequestFilter,
                                @Qualifier("customLoggingFilter") Filter loggingFilter) {
        this.oncePerRequestFilter = oncePerRequestFilter;
        this.loggingFilter = loggingFilter;
    }


    /**
     * Spring Security Password 암호화 처리
     * @return
     * */
    @Bean
    public PasswordEncoder bCryPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain spriSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(httpSecurityCorsConfigurer -> Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests( authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers(PASS_URL_LIST).permitAll()
                            .anyRequest().authenticated())
                    .addFilterBefore(oncePerRequestFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> {
            web.ignoring().requestMatchers("/**");
        };
    }
}
