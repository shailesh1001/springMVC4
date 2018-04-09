package com.websystique.springmvc.configuration;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.websystique.springmvc.viewresolver.Jaxb2MarshallingXmlViewResolver;
import com.websystique.springmvc.model.Pizza; 
import java.util.*;
import com.websystique.springmvc.viewresolver.JsonViewResolver;
import com.websystique.springmvc.viewresolver.ExcelViewResolver;
import com.websystique.springmvc.viewresolver.JsonViewResolver;
import com.websystique.springmvc.viewresolver.JsonViewResolver;
import com.websystique.springmvc.viewresolver.Jaxb2MarshallingXmlViewResolver;
import com.websystique.springmvc.viewresolver.PdfViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {
/*    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
    */
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

    
    @Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		resolvers.add(jaxb2MarshallingXmlViewResolver());
		resolvers.add(jsonViewResolver());
		resolvers.add(jspViewResolver());
		resolvers.add(pdfViewResolver());
		resolvers.add(excelViewResolver());
//		
		resolver.setViewResolvers(resolvers);
		return resolver;
	}
    
    
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}
	
	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}

	
    
    @Bean
	public ViewResolver jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Pizza.class);
		return new Jaxb2MarshallingXmlViewResolver(marshaller);
	}
    
 
}