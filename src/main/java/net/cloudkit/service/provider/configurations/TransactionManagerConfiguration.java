package net.cloudkit.service.provider.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * TransactionManagerConfiguration
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-05-16 2:01 PM
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagerConfiguration {

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
