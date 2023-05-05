package org.launchcode.TasteBuddiesServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMapping(CorsRegistry registry) {
        registry
                .addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Authorization", "Origin", "Content-Type")
                .allowCredentials(true).maxAge(3600);
    }
}
