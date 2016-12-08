package com.spring.jdbc.config;

import org.hibernate.dialect.HSQLDialect;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Class Name : DataConfig<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/510:04<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
@Configuration
public class DataConfig {

    /**
     * Use JNDI data source to configure the database
     * @return
     */
    /*@Bean
    public JndiObjectFactoryBean dataSource() {
        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
        // 指定JNDI中资源的名称
        jndiObjectFB.setJndiName("jdbc/SpittrDS");
        // java应用程序需要将其设置成true，这样给定的jndi-name将会自动添加“java：com/env/”前缀
        jndiObjectFB.setResourceRef(true);
        jndiObjectFB.setProxyInterface(DataSource.class);
        return jndiObjectFB;
    }*/

    /**
     * Use the data source connection pool which is DBCP to configure the database.
     * Other data source connection pools are : c3p0,BoneCP
     * @return
     */
   /* @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h1:tcp://localhost/~/spitter");
        dataSource.setUsername("sa");
        dataSource.setPassword(" ");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        return dataSource;
    }*/

    /**
     * Use a JDBC-Driver data source to configure database
     * Driver-based data connections typically do not have a multithreaded approach,even if
     * there is a new connection created each time the connection is requested,at the cost of
     * performance.It is not recommended to use this method.
     * @return
     */
    /*@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
        dataSource.setUsername("sa");
        dataSource.setPassword(" ");
        return dataSource;
    }*/

    /**
     * Use an embedded data source to configure the database
     * @return
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }


    /**
     * Use jdbcTemplate to insert data
     * @param dataSource
     * @return
     */
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    /**
     * hibernate3使用注解的方式
     * @param ds
     * @return
     */
    @Bean
    public AnnotationSessionFactoryBean sessionFactoryBean(DataSource ds) {
        AnnotationSessionFactoryBean sfb = new AnnotationSessionFactoryBean();
        sfb.setDataSource(ds);
        sfb.setPackagesToScan(new String[]{"com.spring.jdbc.domain"});
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        sfb.setHibernateProperties(properties);
        return sfb;
    }

    /**
     * 添加一个bean后置处理器，它会在所有拥有@Repository注解的类上添加一个通知其，这样就会捕获任何平台相关的异常并以Spring非检查型数据访问异常的形式重新抛出
     * @return
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * 使用容器管理类型的jpa
     * 创建entityManager的bean
     * @param dataSource
     * @param jpaVendorAdapter
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.spring.jdbc.domain");
        return emfb;
    }

    /**
     * 使用hibernate作为jpa的实现，配置bean
     * @return
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.HSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        return adapter;
    }

    /**
     * 让spring理解jpa的注解
     * @return
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
