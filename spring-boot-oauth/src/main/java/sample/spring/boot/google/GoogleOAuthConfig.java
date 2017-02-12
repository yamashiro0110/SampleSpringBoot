package sample.spring.boot.google;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
@Configuration
public class GoogleOAuthConfig {
    @Value("${sample.oauth.google.apikey}")
    private String apiKey;
    @Value("${sample.oauth.google.secret}")
    private String secret;

    private String callbackUrl() {
        return "http://localhost:8080/oauth/google/callback";
    }

    private String secretState() {
        return UUID.randomUUID().toString();
    }

    @Bean("googleOAuthService")
    OAuth20Service oAuth20Service() {
        return new ServiceBuilder()
                .apiKey(this.apiKey)
                .apiSecret(this.secret)
                .callback(this.callbackUrl())
                .state(this.secretState())
                .scope("email")
                .build(GoogleApi20.instance());
    }

}
