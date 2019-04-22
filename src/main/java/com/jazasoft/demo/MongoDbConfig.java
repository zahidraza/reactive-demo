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
    public ReactiveMongoClientFactoryBean mongoClient() {

        ReactiveMongoClientFactoryBean clientFactory = new ReactiveMongoClientFactoryBean();
        clientFactory.setHost("localhost");

        return clientFactory;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MultiTenantMangoDbFactory multiTenantMangoDbFactory) {
        return new ReactiveMongoTemplate(multiTenantMangoDbFactory);
    }

    @Bean
    public MultiTenantMangoDbFactory multiTenantMangoDbFactory(MongoClient mongoClient) {
        return new MultiTenantMangoDbFactory(MongoClients.create(), "test1");
    }
}
