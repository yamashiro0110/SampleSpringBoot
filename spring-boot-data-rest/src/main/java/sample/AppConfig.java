package sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class AppConfig {
}
