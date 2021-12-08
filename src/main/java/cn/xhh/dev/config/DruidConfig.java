/**
 * 在配置了Druid多数据源后被舍弃
 */
//package cn.xhh.study.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * 在修改了Druid的默认数据源配置后，需要添加配置类
// * @author Administrator
// */
//@Configuration
//public class DruidConfig {
//
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    @Bean
//    public DataSource druid(){
//        return new DruidDataSource();
//    }
//}