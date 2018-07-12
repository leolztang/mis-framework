package com.base.framework.config.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@Order
public class ParamValidatorConfig {
    /**
     * 参数验证
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        return new MethodValidationPostProcessor();
    }
}
