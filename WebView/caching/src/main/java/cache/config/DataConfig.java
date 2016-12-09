package cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Class Name : DataConfig<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/99:58<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
@Configuration
@EnableTransactionManagement
public class DataConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.addScript("classpath:schema.sql");
        builder.addScript("classpath:test-data.sql");
        return builder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate (DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
