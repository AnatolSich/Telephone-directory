package com.springAdvanced.Telephonedirectory.config;

import com.springAdvanced.Telephonedirectory.model.User;
import com.springAdvanced.Telephonedirectory.service.PdfHttpMessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan({"com.springAdvanced.Telephonedirectory"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
   /*     registry.addResourceHandler("/test/**").addResourceLocations("/test/").setCachePeriod(0);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(0);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(0);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(0);*/
    }

    @Bean
    public ResourceBundleViewResolver viewResolver() {
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setOrder(1);
        resolver.setBasename("views");
        return resolver;
    }

    //When need only FreeMarkerViewResolver
/*    @Bean
    public FreeMarkerViewResolver viewFreeMarkerResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftlh");
        viewResolver.setCache(false);   //Set to true during production
        viewResolver.setContentType("application/json;charset=UTF-8");
        return viewResolver;
    }*/

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


    // Spring Boot web auto-configuration brings a default configurer
    // we can register  custom configurers without danger to overwrite the default configuration,
    // but we have to put it at the beginning of the list

    @Bean
    public PdfHttpMessageConverter pdfHttpMessageConverter() {
        PdfHttpMessageConverter pdfConverter = new PdfHttpMessageConverter();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_PDF);
        pdfConverter.setSupportedMediaTypes(list);
        pdfConverter.canWrite(User.class, MediaType.APPLICATION_PDF);
        return pdfConverter;
    }

/*    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }*/

    /*    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }*/

    // for straight Spring MVC applications
    // You must remove @EnableWebMvc for your converters to be configured if you extend WebMvcConfigurationSupport.

/*    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2HttpMessageConverter());
        //      WebMvcConfigurer.super.configureMessageConverters(converters);
    }*/

    /*   @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
        WebMvcConfigurationSupport.addDefaultHttpMessageConverters(converters);
    }

*/

/*    @Bean
    MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        return new MappingJackson2XmlHttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .defaultUseWrapper(false)
                        .createXmlMapper(true)
                        .build()
        );
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2XmlHttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .defaultUseWrapper(false)
                        .createXmlMapper(true)
                        .build()
        ));
    }*/

    //The order of checking is always path extension, parameter, Accept header.
    //Enable the use of the URL parameter but instead of using the default parameter, format, we will use mediaType instead.
    //Ignore the Accept header completely.
    //Don't use the JAF, instead specify the media type mappings manually
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("mediaType").
                ignoreAcceptHeader(true).
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("json", MediaType.APPLICATION_JSON).
                mediaType("pdf", MediaType.APPLICATION_PDF);
    }
}

