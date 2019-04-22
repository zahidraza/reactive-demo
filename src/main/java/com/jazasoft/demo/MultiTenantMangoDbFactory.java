package com.jazasoft.demo;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;


public class MultiTenantMangoDbFactory extends SimpleReactiveMongoDatabaseFactory {
//    private final static Logger logger = LoggerFactory.getLogger(MultiTenantMangoDbFactory.class);

    private final String defaultDatabase;

    public MultiTenantMangoDbFactory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        this.defaultDatabase = databaseName;
    }


    @Override
    public MongoDatabase getMongoDatabase() throws DataAccessException {
        final String tlName = CurrentTenantHolder.get();
        final String dbToUse = (tlName != null ? tlName : this.defaultDatabase);
//        logger.debug("Acquiring database: " + dbToUse);
        return getMongoDatabase(dbToUse);
    }

    @Override
    public MongoDatabase getMongoDatabase(String dbName) throws DataAccessException {
//        logger.debug("getMongoDatabase: dbName = {}", dbName);
        return super.getMongoDatabase(dbName);
    }

}
