package com.jops.config;

import com.jops.mapper.ServerReadConverter;
import com.jops.mapper.ServerWriteConverter;
import com.jops.service.EncryptionService;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MKowynia on 9/28/15.
 */
@Configuration
public class JOpsDataConfig  {

    @Value( "${mongo.host}" )
    private String mongoHost;

    @Value( "${mongo.port}" )
    private String mongoPort;

    @Value( "${mongo.db.name}" )
    private String dbName;

    @Value( "${encryptor.secret}" )
    private String secret;

    @Bean
    public EncryptionService encryptionService(){
        return new EncryptionService(secret);
    }

    @Bean
    public ServerReadConverter serverReadConverter(){
        return new ServerReadConverter(encryptionService());
    }
    @Bean
    public ServerWriteConverter serverWriteConverter(){
        return new ServerWriteConverter(encryptionService());
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(mongoHost,Integer.valueOf(mongoPort));
        return new SimpleMongoDbFactory(mongoClient, dbName);
    }

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(serverReadConverter());
        converters.add(serverWriteConverter());
        return new CustomConversions(converters);
    }

    @Bean
    public MappingMongoConverter mongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        return mongoConverter;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(),mongoConverter());
    }

}
