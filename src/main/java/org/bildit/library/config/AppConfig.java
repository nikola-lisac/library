package org.bildit.library.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Ognjen Mišiæ
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.bildit.library*")
@EnableTransactionManagement
// ova anotacija kaže konfiguraciji gdje se nalazi database.properties file (u
// root folderu u ovom sluèaju)
public class AppConfig extends WebMvcConfigurerAdapter {

	// definišemo tip view fajlova sa kojim radimo i gdje se oni nalaze
	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	// definišemo naše custom validation poruke u properties fajlu, neæemo ih
	// hardkodovati
//	@Bean
//	public MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

	// uèitavanje statiènih resursa kao što su css, js i ostali fajlovi bla bla
	// bla
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
}
