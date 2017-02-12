package sample.spring.boot.yahoo.japan;

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
public class YahooJapanOAuthConfig {
    @Value("${sample.oauth.yahooJapan.apikey}")
    private String apiKey;
    @Value("${sample.oauth.yahooJapan.secret}")
    private String secret;

    private String callbackUrl() {
        return "http://localhost:8080/oauth/yahoo_japan/callback";
    }

    private String secretState() {
        return UUID.randomUUID().toString();
    }

    @Bean("yahooJapanOAuthService")
    OAuth20Service oAuth20Service() {
        return new ServiceBuilder()
                .apiKey(this.apiKey)
                .apiSecret(this.secret)
                .callback(this.callbackUrl())
                .state(this.secretState())
                .responseType("code")
                .scope("openid profile")
                .build(YahooJapanConnectApi20.instance());
    }

}
