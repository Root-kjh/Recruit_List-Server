package com.DrK.config;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = {"com.DrK.repositories"})
@EnableJpaRepositories(basePackages = {"com.DrK.repositories"})
@EnableTransactionManagement
public class DB{
	
	String host="13.125.62.254";
	
	public MongoDbFactory mongoDbFactory() throws Exception{
	
		ServerAddress serverAddress=new ServerAddress(host, 27017);
		MongoCredential mongoCredential=MongoCredential.createCredential("Recruit_List", "Recruit_List", "Recruit_List".toCharArray());
		
		MongoClient mongoClient=new MongoClient(serverAddress, Arrays.asList(mongoCredential));
		
		SimpleMongoDbFactory simpleMongoDbFactory=new SimpleMongoDbFactory(mongoClient, "Recruit_List");
		
		return simpleMongoDbFactory;
	}
	
	@Bean
	public MongoTemplate mognTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource datasource=new DriverManagerDataSource("jdbc:mysql://13.125.62.254/Recruit_List", "Recruit_List", "Recruit_List");
		return datasource;
	}
	
	private Properties Properties() {
		Properties properties=new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.user_sql_comments", "true");
		properties.put("hibernate.id.new_generator_mappings", "true");
		properties.put("hibernate.hbm2ddl.auto", "none");
		return properties;
	}
	
	@Bean
	public EntityManagerFactory entityMnagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.DrK.entities");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(Properties());
		return factoryBean.getObject();
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityMnagerFactory());
	}
}