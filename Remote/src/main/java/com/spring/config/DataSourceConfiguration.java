package com.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * Class Name : DataSourceConfiguration<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/510:51<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 配置开发数据源
     * Configure the development data source
     * @return
     */
    @Profile("development")
    @Bean
    public DataSource embeddedDataSource() {
        return  new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }

    /**
     * QA 数据源
     * QA data source
     * @return
     */
    @Profile("qa")
    @Bean
    public DataSource data() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword(" ");
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxActive(10);
        return basicDataSource;
    }

    /**
     * 生产环境的数据源
     * The data source for the production environment .
     * @return
     */
    @Profile("production")
    @Bean
    public DataSource dataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/SpittrDS");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        return (DataSource) jndiObjectFactoryBean.getObject();
    }
}
