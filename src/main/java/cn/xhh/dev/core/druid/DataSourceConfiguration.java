package cn.xhh.dev.core.druid;


import cn.xhh.dev.common.enums.DataSourcesType;
import cn.xhh.dev.core.datasource.DynamicDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类
 * @author Administrator
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 主库
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.setDataSource(DruidDataSourceBuilder.create().build());
    }

    /**
     * 从库
     * @param dataSourceProperties
     * @return DataSource
     */
    @Bean
    @ConditionalOnProperty( prefix = "spring.datasource.druid.slave", name = "enable", havingValue = "true")//是否开启数据源开关---若不开启 默认适用默认数据源
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.setDataSource(DruidDataSourceBuilder.create().build());
    }

    /**
     * 1.@DependsOn({"masterDataSource","slaveDataSource"})配合Springboot启动类，解决Druid多数据源循环依赖的问题
     * 设置数据源
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    @DependsOn({"masterDataSource","slaveDataSource"})
    public DynamicDataSource dynamicDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DynamicDataSource dynamicDataSource = DynamicDataSource.build();
        targetDataSources.put(DataSourcesType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourcesType.SLAVE.name(), slaveDataSource);
        //默认数据源配置 DefaultTargetDataSource
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        //额外数据源配置 TargetDataSources
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }
}
