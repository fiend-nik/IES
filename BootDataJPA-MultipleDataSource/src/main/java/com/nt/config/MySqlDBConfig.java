package com.nt.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= "com.nt.repo.promotions", 
						entityManagerFactoryRef = "mysqlEMF", 
						transactionManagerRef = "mysqlTxMgmr")
public class MySqlDBConfig {
	
	@Bean
	@ConfigurationProperties("mysql.datasource")
	public DataSource createDs() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="mysqlEMF")
	public LocalContainerEntityManagerFactoryBean 
					createEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(createDs())
				.packages("com.nt.model.promotions")
				.properties(props)
				.build();
	}
	
	@Bean(name="mysqlTxMgmr")
	public PlatformTransactionManager createTransactionManger(@Qualifier("mysqlEMF") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
}
