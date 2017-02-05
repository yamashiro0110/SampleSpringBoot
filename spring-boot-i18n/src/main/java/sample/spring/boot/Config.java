package sample.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yamashiro-r on 2017/02/04.
 */
@Configuration
public class Config extends WebMvcConfigurerAdapter {
    @Autowired
    private MessageSource messageSource;

    @Bean
    LocalValidatorFactoryBean localValidatorFactoryBean() {
        final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(this.messageSource);
        return localValidatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return localValidatorFactoryBean();
    }
}
