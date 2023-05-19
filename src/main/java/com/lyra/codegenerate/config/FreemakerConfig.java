package com.lyra.codegenerate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/5/18
 * @Description
 */
@Configuration
public class FreemakerConfig {
    @Bean
    public freemarker.template.Configuration configuration() {
        return new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_26);
    }
}
