package com.finalhints.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author amit.bhoraniya
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new ApiKey("Authentication", "Authorization", "header"));
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.finalhints.controller")).paths(PathSelectors.any())
				.build().apiInfo(getMetaData()).securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(schemeList);
	}

	private ApiInfo getMetaData() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Support Desk Portal RestAPI")
				.description("Support Desk Portal RestAPI Documentation").version("1.0b").build();
		return apiInfo;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(getPaths()).build();
	}

	Predicate<String> getPaths() {
		return Predicates.not(Predicates.or(PathSelectors.regex("/user/auth"), PathSelectors.regex("/user")));
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("Authentication", authorizationScopes));
	}
}