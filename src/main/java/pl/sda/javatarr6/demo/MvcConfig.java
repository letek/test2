package pl.sda.javatarr6.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/main").setViewName("main");
		registry.addViewController("/").setViewName("forward:/listzadanie");
		//registry.addViewController("/listzadanie").setViewName("listzadanie");
		//registry.addViewController("/addzadanie").setViewName("addzadanie");
		registry.addViewController("/login").setViewName("login");

	}

}
