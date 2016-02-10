package server;

import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerServiceImpl implements ServerService {
	
	@Autowired
	private ApplicationContext rootContext;
	 
	@Override
	public Server createWebServer() throws Exception {
		
		Server server = new Server(8080);
		
		ServletContextHandler handler = new ServletContextHandler();
		handler.setErrorHandler(null);
		handler.setContextPath("/");
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(WebConfig.class);
		ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
		handler.addServlet(servletHolder, "/");

		server.setHandler(handler);
		
		
		
		return server;
		
	}

	@Override
	public void start(Server server) throws Exception {
		server.start();
		
	}
	
	@Configuration
    @ComponentScan(
            basePackages = {"controller"},
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class)
            })
    static class WebConfig extends WebMvcConfigurationSupport {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:__controller/");
        }

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("forward:/index.html");
            
        }
        
        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            converter.setObjectMapper(objectMapper);
            converters.add(converter);
            super.configureMessageConverters(converters);
        }
        
    }


}
