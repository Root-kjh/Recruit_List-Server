package com.DrK.config;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableMongoRepositories(basePackages = {"com.DrK.repositories"})
@EnableJpaRepositories(basePackages = {"com.DrK.repositories"})
@EnableTransactionManagement
public class DB extends AbstractMongoConfiguration{
	
	String host="13.125.62.254";

	@Override
	public MongoClient mongoClient() {
		MongoCredential credential=MongoCredential.createCredential("Recruit_List", "Recruit_List", "Recruit_List".toCharArray());
		return new MongoClient(new ServerAddress(host, 27017), Arrays.asList(credential));
	}

	@Override
	protected String getDatabaseName() {
		return "Recruit_List";
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception{
		return new MongoTemplate(mongoClient(), getDatabaseName());
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
	public DataSource dataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+host+"/Recruit_List");
		dataSource.setUsername("Recruit_List");
		dataSource.setPassword("Recruit_List");
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.DrK.entities");
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