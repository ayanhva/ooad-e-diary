package ooad.project.ediary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/e-diary/*")
                .allowedOrigins("http://localhost:8444")
                .allowedMethods("GET", "POST")
                .allowedHeaders("")
                .allowCredentials(true);
    }
}