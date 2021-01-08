package com.project.cars.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(apis())
				.paths(PathSelectors.any()).build()
				.globalOperationParameters(Collections
						.singletonList(new ParameterBuilder().name("Authorization").description("Bearer token")
								.modelRef(new ModelRef("string")).parameterType("header").required(false).build()))
				.apiInfo(apiInfo());
	}
	
	private Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage("com.project.cars");
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().contact(new Contact("Ivan Mateus", "", "")).title("Carros")
				.description("Documentação API dos Carros").license("Apache Licence Version 2.0")
				.licenseUrl("https://apache.org").version("1.0").build();

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}