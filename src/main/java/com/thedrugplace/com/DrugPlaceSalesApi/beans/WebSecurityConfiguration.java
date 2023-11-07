//package com.thedrugplace.com.DrugPlaceSalesApi.beans;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.logging.Logger;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    private static final Logger logger = Logger.getLogger(WebSecurityConfiguration.class.getName());
//
//    /**
//     * Configure security settings for the HTTP requests.
//     *
//     * @param http HttpSecurity configuration.
//     * @throws Exception Exception thrown if configuration fails.
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .headers()
//                .frameOptions()
//                .disable();
//
//        // Log configuration
//        logger.info("HTTP security configuration successfully applied.");
//    }
//
//    /**
//     * Configure Cross-Origin Resource Sharing (CORS) settings for the application.
//     *
//     * @return WebMvcConfigurer to handle CORS configuration.
//     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                // Configure allowed origins for CORS
//                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//                logger.info("CORS configuration applied for 'http://localhost:3000'");
//            }
//        };
//    }
//}
