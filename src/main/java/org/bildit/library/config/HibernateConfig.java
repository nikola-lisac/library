package org.bildit.library.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ognjen Mišiæ Klasa konfiguracije data sourcea i hibernatea
 *
 */
@Configuration
@ComponentScan("org.bildit.library*")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class HibernateConfig {

	// imena varijabli koje æe environment objekat da gleda u properties fajlu
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_HIBERNATE_DDL = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";

	private static int batchSize;

	// ovaj objekat vadi varijable iz properties fajla
	@Resource
	private Environment environment;

	public int getBatchSize() {
		return batchSize;
	}

	// datasource vuèe podatke iz properties fajla kroz environment objekat
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		batchSize = Integer.valueOf(environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
		return dataSource;
	}

	// session factory je thread safe objekat koji nam kreira sesije koje æemo
	// kopristiti za interakciju sa bazom (sesije su thread unsafe)
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource());
		sf.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sf.setHibernateProperties(hibernateProperties());
		return sf;
	}

	// ovaj bean upravlja transakcijama, on u kombinaciji sa @transactional
	// anotacijom osigurava transakcije i u sluèaju pada ih sam rollbackuje
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sf) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sf);
		return txManager;
	}

	// properties za hibernate..
	private Properties hibernateProperties() {
		return new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				setProperty(PROPERTY_NAME_HIBERNATE_DDL, environment.getProperty(PROPERTY_NAME_HIBERNATE_DDL));
				setProperty(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
				setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
						environment.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
				setProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
						environment.getProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
			}
		};
	}
}
