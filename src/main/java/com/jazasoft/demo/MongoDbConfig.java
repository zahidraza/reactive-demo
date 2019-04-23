package com.jazasoft.demo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoClientFactoryBean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoDbConfig {

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MultiTenantMongoDbFactory multiTenantMongoDbFactory) {
        return new ReactiveMongoTemplate(multiTenantMongoDbFactory);
    }

    @Bean
    public MultiTenantMongoDbFactory multiTenantMangoDbFactory(MongoClient mongoClient) {
        return new MultiTenantMongoDbFactory(mongoClient, "test1");
    }

    @Bean
    public ReactiveMongoClientFactoryBean mongoClient() {
        ReactiveMongoClientFactoryBean clientFactory = new ReactiveMongoClientFactoryBean();
        clientFactory.setHost("localhost");
        return clientFactory;
    }
}
