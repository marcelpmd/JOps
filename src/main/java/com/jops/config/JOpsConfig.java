package com.jops.config;

import com.jops.mapper.ServerReadConverter;
import com.jops.mapper.ServerWriteConverter;
import com.jops.service.EncryptionService;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by MKowynia on 9/3/15.
 */
@Configuration
@Import(JOpsDataConfig.class)
@PropertySource("classpath:jops.properties")
@ComponentScan(basePackages = {"com.jops.dao","com.jops.service","com.jops.mapper"})
public class JOpsConfig {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }



}
