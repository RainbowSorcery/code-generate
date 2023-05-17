package com.lyra.codegenerate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("lyra代码生成器")
                        .description("根据规则自动生成MyBatis plus、spring data mongo、Spring boot data jpa代码。")
                        .version("v1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Lyra Blog")
                        .url("https://www.cnblogs.com/lyraHeartstrings/"));
    }


}
