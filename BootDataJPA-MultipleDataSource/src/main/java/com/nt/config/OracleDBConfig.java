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
@EnableJpaRepositories(basePackages= "com.nt.repo.prod", 
						entityManagerFactoryRef = "oracleEMF", 
						transactionManagerRef = "oracleTxMgmr")
public class OracleDBConfig {
	
	@Bean
	@ConfigurationProperties("oracle.datasource")
	@Primary
	public DataSource createDs() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="oracleEMF")
	@Primary
	public LocalContainerEntityManagerFactoryBean 
					createEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(createDs())
				.packages("com.nt.model.prod")
				.properties(props)
				.build();
	}
	
	@Bean(name="oracleTxMgmr")
	@Primary
	public PlatformTransactionManager createTransactionManger(@Qualifier("oracleEMF") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
}
