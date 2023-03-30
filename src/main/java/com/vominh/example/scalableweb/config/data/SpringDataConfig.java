package com.vominh.example.scalableweb.config.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.ClassUtils;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.vominh.example.scalableweb.repo")
public class SpringDataConfig {

    private final AppDataSourceRoute dataSourceRoute;

    public SpringDataConfig(AppDataSourceRoute dataSourceRoute) {
        this.dataSourceRoute = dataSourceRoute;
    }

    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(dataSourceRoute);
    }


    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(AppDataSourceRoute dataSourceRoute) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSourceRoute);
        String entities = ClassUtils.getPackageName("com.vominh.example.scalableweb.entity");
        String converters = ClassUtils.getPackageName(Jsr310JpaConverters.class);
        entityManagerFactoryBean.setPackagesToScan(entities, converters);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    @ConfigurationProperties("spring.jpa.properties.hibernate")
    public Properties jpaHibernateProperties() {
        return new Properties();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("jpaTxManager") PlatformTransactionManager wrapped) {
        return new ReplicaAwareTransactionManager(wrapped);
    }

    @Bean(name = "jpaTxManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
