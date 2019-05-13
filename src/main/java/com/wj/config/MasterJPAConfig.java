package com.wj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author jun.wang
 * @title: MasterConfig
 * @projectName ownerpro
 * @description: jpa数据源配置
 * @date 2019/3/2814:07
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages= { "com.wj.repository" }) //设置Repository所在位置
public class MasterJPAConfig {

    //@Primary //springboot默认是多数据源，所以你要指定一个主数据源，不然会错误
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") //需要导入配置
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /*@Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }*/


    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabase(Database.MYSQL);//这里指定的你数据库的类型
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.wj.entity");//这个是你entity所在的包
        factory.setDataSource(dataSource());
        factory.setPersistenceUnitName("primaryPersistenceUnit");
        return factory;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
