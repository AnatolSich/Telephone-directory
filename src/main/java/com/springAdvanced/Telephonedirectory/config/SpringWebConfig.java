package com.springAdvanced.Telephonedirectory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan({"com.springAdvanced.Telephonedirectory"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public ResourceBundleViewResolver viewResolver() {
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setOrder(1);
        resolver.setBasename("views");
        System.out.println();
        return resolver;
    }

   // with Spring Boot is automatically sets the @EnableTransactionManagement annotation
    // and creates a PlatformTransactionManager (have a spring-data-* or spring-tx dependencies on the classpath)
/*
    @Bean
    public DataSource dataSource() {
      return new HikariDataSource();
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
*/

}