package com.jazasoft.demo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;


public class MultiTenantMongoDbFactory extends SimpleReactiveMongoDatabaseFactory {
    private final String defaultDatabase;

    public MultiTenantMongoDbFactory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        this.defaultDatabase = databaseName;
    }

    @Override
    public MongoDatabase getMongoDatabase() throws DataAccessException {
        final String tlName = CurrentTenantHolder.get();
        final String dbToUse = (tlName != null ? tlName : this.defaultDatabase);
        return super.getMongoDatabase(dbToUse);
    }

}
