package com.kjplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

	private boolean swaggerShow = true;

	/**
	 * 系统接口文档构建
	 * 
	 * @return
	 */
	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(this.swaggerShow).groupName("kjplus").apiInfo(apiInfo()).genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
				.forCodeGeneration(true).pathMapping("")// base，最终调用接口后会和paths拼接在一起
				.select().apis(RequestHandlerSelectors.basePackage("com.kjplus.rest")) // swagget扫描目录
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("康佳全流程项目API文档").description("修改人:宋岳宾").version("1.0").build();
	}
}
