package com.purang.manifest.infrastructure.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@EnableKnife4j
@EnableSwaggerBootstrapUi
public class SwaggerConfig {

    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder().title(name)
                .description(description)
                .version(version)
                .build();
    }

    @Bean
    public Docket FastlinkApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("企业票据清单api", "企业票据清单api", "1.0"))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/bill/manifest/**"))
                .build()
                .groupName("票据api")
                .pathMapping("/");
    }
}
