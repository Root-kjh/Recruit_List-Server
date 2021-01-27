package com.DrK.config;

import java.util.Collections;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableMongoRepositories(basePackages = {"com.DrK.Repositories"})
@EnableJpaRepositories(basePackages = {"com.DrK.Repositories"})
@EnableTransactionManagement
public class DB{
	
	String host="kjh-projects.kro.kr";
	// String host="localhost";

	@Bean
	public MongoTemplate mongoTemplate() throws Exception{
		// MongoClient mongoClient = MongoClients.create("mongodb://RecruitList:RecruitList@" + host + ":27017/RecruitList");
		// return new MongoTemplate(mongoClient, "RecruitList");

		com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(
			new ServerAddress(host, 27017), 
			MongoCredential.createCredential("RecruitList", "RecruitList", "RecruitList".toCharArray()),
			MongoClientOptions.builder().build()
		);
		return new MongoTemplate(mongoClient, "RecruitList");
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+host+"/RecruitList?serverTimezone=UTC");
		dataSource.setUsername("RecruitList");
		dataSource.setPassword("RecruitList");
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.DrK.Entities");
		factory.setDataSource(dataSource());
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager=new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
}