package org.anwang.safe.server.framework.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.anwang.safe.server.framework.web.interceptors.LogTraceBuildInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    MessageSource messageSource;

    @Autowired
    LogTraceBuildInterceptor logTraceBuildInterceptor;

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    @Bean
    protected Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        try {
            validator.setValidationMessageSource( messageSource );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validator;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LOG
        addMVCLogInterceptor( registry );
    }

    private void addMVCLogInterceptor( InterceptorRegistry registry ){
        List<String> logExcludePath = Arrays.asList(
                "/swagger-resources" ,
                "/webjars/**" ,
                "/error",
                "/doc.html"
        );
        registry.addInterceptor( logTraceBuildInterceptor )
                .excludePathPatterns(logExcludePath).addPathPatterns("/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

}
