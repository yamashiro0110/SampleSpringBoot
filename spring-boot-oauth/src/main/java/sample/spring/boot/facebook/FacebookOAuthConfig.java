package sample.spring.boot.facebook;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Created by yamashiro-r on 2017/03/13.
 */
@Configuration
public class FacebookOAuthConfig {
    @Value("${sample.oauth.facebook.apikey}")
    private String apiKey;
    @Value("${sample.oauth.facebook.secret}")
    private String secret;

    private String callbackUrl() {
        return "http://localhost:8080/oauth/facebook/callback";
    }

    private String secretState() {
        return UUID.randomUUID().toString();
    }

    @Bean("facebookOAuthService")
    OAuth20Service oAuth20Service() {
        return new ServiceBuilder()
                .apiKey(this.apiKey)
                .apiSecret(this.secret)
                .callback(this.callbackUrl())
                .state(this.secretState())
                .scope("email")
                .build(FacebookApi.instance());
    }

}
