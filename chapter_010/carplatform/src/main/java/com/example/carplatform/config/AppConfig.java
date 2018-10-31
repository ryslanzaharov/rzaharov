//package com.example.carplatform.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableConfigurationProperties
//public class AppConfig {
//
//    @Primary
//    @Bean(name ="dataSource")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "prodJdbc")
//    public JdbcTemplate prodJdbcTemplate(@Qualifier("dataSource") DataSource prodDataSource){
//        return new JdbcTemplate(prodJdbcTemplate);
//    }
//
//    @Bean(name = "devDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.dev")
//    public DataSource devDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "devJdbc")
//    public JdbcTemplate devJdbcTemplate(@Qualifier("devDataSource") DataSource devDataSource) {
//        return new JdbcTemplate(devDataSource);
//    }
//}
