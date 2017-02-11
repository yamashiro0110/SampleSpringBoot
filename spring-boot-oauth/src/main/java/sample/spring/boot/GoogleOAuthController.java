package sample.spring.boot;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by yamashiro-r on 2017/02/11.
 */
@Controller
@RequestMapping("/oauth/google")
public class GoogleOAuthController {
    @Resource(name = "googleOAuthService")
    private OAuth20Service oAuth20Service;

    private String api() {
        return "https://www.googleapis.com/oauth2/v2/userinfo";
    }

    private Map<String, String> apiParams() {
        return new LinkedHashMap<String, String>() {
            {
                put("prompt", "select_account");
            }
        };
    }

    private String profile(final OAuth2AccessToken accessToken) throws InterruptedException, ExecutionException, IOException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, this.api());
        this.oAuth20Service.signRequest(accessToken, request);
        final Response response = this.oAuth20Service.execute(request);
        return response.getBody();
    }

    @GetMapping
    String redirect() {
        return "redirect:" + this.oAuth20Service.getAuthorizationUrl(this.apiParams());
    }

    @GetMapping("callback")
    @ResponseBody
    String callback(@RequestParam("code") final String code) throws InterruptedException, ExecutionException, IOException {
        final OAuth2AccessToken accessToken = this.oAuth20Service.getAccessToken(code);

        if (StringUtils.isEmpty(accessToken.getRefreshToken())) {
            return this.profile(accessToken);
        }

        final OAuth2AccessToken refreshToken = this.oAuth20Service.refreshAccessToken(accessToken.getRefreshToken());
        return this.profile(refreshToken);
    }
}

@Configuration
class GoogleConfigu {
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