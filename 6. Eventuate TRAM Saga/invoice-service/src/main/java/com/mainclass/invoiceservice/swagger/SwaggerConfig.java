package com.mainclass.invoiceservice.swagger;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("com.mainclass.invoiceservice"))
	            .build()
	            .pathMapping("/")
	            .genericModelSubstitutes(ResponseEntity.class, CompletableFuture.class)
	            .alternateTypeRules(
	                    newRule(typeResolver.resolve(DeferredResult.class,
	                                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                            typeResolver.resolve(WildcardType.class))
	            )
	            .useDefaultResponseMessages(false)
	            ;
	  }

	  @Autowired
	  private TypeResolver typeResolver;
}
