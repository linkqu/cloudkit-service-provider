package net.cloudkit.service.provider.configurations;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * DataSourceConfiguration
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-05-16 10:17 AM
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Bean
    public DataSource dataSource() {
        ///
        String classPath = Objects.requireNonNull(
            DataSourceConfiguration.class.getClassLoader().getResource("")
        ).toString();
        logger.info("ClassPath: {}", classPath);

        DruidDataSource pooledDataSource = null;
        try {
            pooledDataSource = new DruidDataSource();
            pooledDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            pooledDataSource.setUrl("jdbc:mysql://10.33.1.215:3306/test?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false");
            pooledDataSource.setUsername("root");
            pooledDataSource.setPassword("rootroot");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pooledDataSource;
    }
}
