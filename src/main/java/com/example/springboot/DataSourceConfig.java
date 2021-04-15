/*
 * Copyright 2018 (C) Oracle Corporation
 *
 * Created on : 01-07-2018
 *
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  |               | Initial Create.
 *         | 01-07-2018    |
 *---------|---------------|---------------------------------------------------
 *         | author        | Defect ID 1/Description
 *         | dd-mm-yyyy    |
 *---------|---------------|---------------------------------------------------
 */
package com.example.springboot;

import oracle.jdbc.datasource.impl.OracleDataSource;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * The Class JPAContextConfig.
 *
 *
 * @version 1.0
 * @since 01-07-2018
 */
@Configuration
public class DataSourceConfig {
    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * Data source properties.
     *
     * @return the data source properties
     */
    @Value("${dc.db.host}")
    private String host;

    @Value("${dc.db.port}")
    private String port;

    @Value("${dc.db.serviceName}")
    private String serviceName;

    @Value("${dc.db.user}")
    private String primaryUser;

    @Value("${dc.db.password}")
    private String primaryPassword;

    @Value("${dc.db.maxPoolSize:10}")
    private int maximumPoolSize;

    @Value("${dc.db.minIdle:0}")
    private int mininumPoolSize;

    @Value("${dc.db.connectionTimeout:30}")
    private int connectionTimeout;

    @Value("${dc.db.idleTimeout:30}")
    private int idleTimeout;

    @Value("${dc.db.maxLifetime:30}")
    private int maxLifetime;


    private String jdbcUrl() {
        String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + serviceName;
        return url;
    }

    private DataSource createDataSource() throws SQLException {
        String url = jdbcUrl();
        String username = primaryUser;
        String password = primaryPassword;

        logger.info("URL for db connection for DataSourceConfig is: {}, Username: {}, Password: {}",url,username,password);

        //DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        //HikariDataSource dataSource = null;
        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
//        OracleDataSource dataSource = new OracleDataSource();

//            //dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//            dataSource.setURL(url);
//            dataSource.setUser(username);
//            //KmsClient kmsClient = new KmsClient(kmsDatabag);
//            dataSource.setPassword(password);
        //dataSource.type(HikariDataSource.class);
        // dataSource = (HikariDataSource) dataSourceBuilder.build();

/*            dataSource.setMaximumPoolSize(maximumPoolSize);
            dataSource.setMinimumIdle(minimumIdle);
            dataSource.setConnectionTimeout(SECONDS.toMillis(connectionTimeout));
            dataSource.setIdleTimeout(SECONDS.toMillis(idleTimeout));
            dataSource.setMaxLifetime(SECONDS.toMillis(maxLifetime));*/

        poolDataSource.setURL(url);
        poolDataSource.setUser(username);
        poolDataSource.setPassword(password);
        poolDataSource.setConnectionFactoryClassName("oracle.jdbc.OracleDriver");

        poolDataSource.setMaxPoolSize(maximumPoolSize);
        poolDataSource.setMinPoolSize(mininumPoolSize);

        // close inactive connections within the pool after 60 seconds
//        poolDataSource.setInactiveConnectionTimeout(connectionTimeout);
//        poolDataSource.setAbandonedConnectionTimeout(10);
//        poolDataSource.setTimeToLiveConnectionTimeout(120);
//        poolDataSource.setTimeoutCheckInterval(60);
//        poolDataSource.setConnectionHarvestMaxCount(3);
//        poolDataSource.setConnectionHarvestTriggerCount(2);
//        poolDataSource.setConnectionWaitTimeout(10);
//        poolDataSource.setMaxStatements(10);
//        poolDataSource.setMaxIdleTime(idleTimeout);
        return poolDataSource;

    }

    @Primary
    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource dataSource() throws SQLException {
        return createDataSource();
    }

    @Bean(name = "oracleDataSource", destroyMethod = "")
    public DataSource oracleDataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setUser(primaryUser);
        ds.setPassword(primaryPassword);
        ds.setURL(jdbcUrl());
        ds.setImplicitCachingEnabled(true);
        return ds;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
