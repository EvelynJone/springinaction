package com.spring.jdbc.data.springdata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Class Name : SpringDataJpaConfig<BR>
 * Descripe : spring data 配置类<BR>
 *     EnableJpaRepositories: 要求springdata创建jpaRepository
 *     repositoryImplementationPostfix = "impl": 该属性用来设置Repository实现类的后缀，默认为Impl（例如SpitterRepository的实现类的名称为SpitterRepositoryImpl）
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/710:48<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.spring.jdbc.data.springdata",repositoryImplementationPostfix = "impl")
public class SpringDataJpaConfig {

    @Bean
    public DataSource dataSource() {
        return  new EmbeddedDatabaseBuilder()
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    public Object emf () {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName("spitter");
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        return emf;
    }
}
