package com.example.springboot;


import com.example.springboot.jms.JmsErrorHandler;
import com.example.springboot.jms.JmsMessageListener;
import oracle.jms.AQjmsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;
import javax.sql.DataSource;

@Configuration
public class JmsConfig {

//    @Value("${spring.profiles.active:#{wls}}")
//    private String activeProfile;

    @Value("${config.db.queue}")
    private String queueName;

    @Value("${config.db.topic}")
    private String topicName;

    @Value("${config.queue.concurrency.limit:1-3}")
    private String concurrencyLimit;

    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource oracleDataSource;


    @Bean
    public JmsMessageListener queueMessageListener() {
        return new JmsMessageListener();
    }



    @Bean(name = "jobRequestJmsTemplate")
    public JmsTemplate jmsTemplate() throws Exception {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestinationName(queueName);
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.setConnectionFactory(queueConnectionFactory());
        return jmsTemplate;
    }

//    @Bean(name = "dcBroadcastJmsTemplate")
//    public JmsTemplate dcBroadcastJmsTemplate() throws Exception {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setDefaultDestinationName(topicName);
//        jmsTemplate.setPubSubDomain(true);//Imp for topic
//        jmsTemplate.setSessionTransacted(true);
//        jmsTemplate.setConnectionFactory(topicConnectionFactory());
//        return jmsTemplate;
//    }

//    @Bean(name = "commonJmsTemplate")
//    public JmsTemplate commonJmsTemplate() throws Exception {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setSessionTransacted(true);
//        jmsTemplate.setConnectionFactory(queueConnectionFactory());
//        return jmsTemplate;
//    }


    @Bean
    public DefaultMessageListenerContainer queueMessageListenerContainer() {
        DefaultMessageListenerContainer cont = new DefaultMessageListenerContainer();
        cont.setMessageListener(queueMessageListener());
        cont.setConnectionFactory(queueConnectionFactory());
        cont.setDestinationName(queueName);
        setMessageListenerContainerProps(cont);
        return cont;
    }

//    @Bean
//    public DefaultMessageListenerContainer topicMessageListenerContainer() {
//        DefaultMessageListenerContainer cont = new DefaultMessageListenerContainer();
//
//        cont.setMessageListener(topicMessageListener());
//        cont.setConnectionFactory(topicConnectionFactory());
//        cont.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
//        cont.setSessionTransacted(true);
//        cont.setDestinationName(topicName);
//        cont.setPubSubDomain(true);//Imp for topic
//        cont.setSubscriptionDurable(true);
//        cont.setSubscriptionName("jmsTopicMessageListener");
//        cont.setClientId("DC_BROADCASTER_TOPIC");
//        cont.setConcurrency("1");
//        return cont;
//    }

    private void setMessageListenerContainerProps(DefaultMessageListenerContainer container){
        //A durable subscriber registers a durable subscription by specifying a unique identity that is retained by the JMS provider.If a durable subscription has no active subscriber, the JMS provider retains the subscription's messages until they are received by the subscription or until they expire.
        //This is required for single VM, but in multi vm this is not required
        //cont.setSubscriptionDurable(true);
        container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setSessionTransacted(true);
        container.setConcurrency(concurrencyLimit);
        container.setMaxMessagesPerTask(200);
        container.setReceiveTimeout(10000);//milliseconds
        container.setIdleTaskExecutionLimit(50);
        container.setIdleConsumerLimit(20);
        container.setErrorHandler(new JmsErrorHandler());
    }

//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }

    @Bean(name = "queueConnectionFactory")
    public ConnectionFactory queueConnectionFactory() {
        QueueConnectionFactory queueConnectionFactory;
        try {
            queueConnectionFactory = AQjmsFactory.getQueueConnectionFactory(oracleDataSource);
        } catch (JMSException e) {
            throw new RuntimeException("cannot get connection factory for Queue");
        }
        return queueConnectionFactory;
    }

    @Bean(name = "topicConnectionFactory")
    public ConnectionFactory topicConnectionFactory() {
        TopicConnectionFactory topicConnectionFactory = null;
        try {
            topicConnectionFactory = AQjmsFactory.getTopicConnectionFactory(oracleDataSource);
        } catch (Exception e) {
            throw new RuntimeException("cannot get connection factory for Topic.");
        }
        return topicConnectionFactory;
    }


    //Note - Do not destroyMethod = "" from Bean else it will delete the datasource from weblogic jndi tree on redeploy.
   // @Primary
    //@Bean(name = "dataSource", destroyMethod = "")
    //@ConfigurationProperties(prefix = "spring.datasource.configuration")
   /* public DataSource messageDataSource() {
        try {
            //if(StringUtils.hasText(activeProfile) && ("tomcat".equalsIgnoreCase(activeProfile) || "test".equalsIgnoreCase(activeProfile))) {
                PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
                DataSourceProperties dataSourceProperties = dataSourceProperties();

                poolDataSource.setURL(dataSourceProperties.getUrl());
                poolDataSource.setUser(dataSourceProperties.getUsername());
                poolDataSourc.setPassword(dataSourceProperties.getPassword());
                poolDataSource.setConnectionFactoryClassName("oracle.jdbc.OracleDriver");
                // close inactive connections within the pool after 60 seconds
                poolDataSource.setInactiveConnectionTimeout(300);
                poolDataSource.setAbandonedConnectionTimeout(0);
                poolDataSource.setTimeToLiveConnectionTimeout(0);
                poolDataSource.setTimeoutCheckInterval(30);
                return poolDataSource;
//            } else {
//                 return (new JndiTemplate()).lookup(dataSourceProperties().getJndiName(), DataSource.class);
//            }
        } catch (Exception e) {
            throw new RuntimeException("driver not found - ", e);
        }
    }*/

/*
    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(messageDataSource());
    }*/
}
