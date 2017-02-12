package sample.spring.boot.yahoo.japan;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
@Controller
@RequestMapping("/oauth/yahoo_japan")
public class YahooJapanOAuthController {
    @Resource(name = "yahooJapanOAuthService")
    private OAuth20Service yahooJapanOAuthService;

    private String authUrl() {
        return this.yahooJapanOAuthService.getAuthorizationUrl();
    }

    private String apiUrl() {
        return "https://userinfo.yahooapis.jp/yconnect/v1/attribute?schema=openid";
    }

    private String user(final OAuth2AccessToken accessToken) throws InterruptedException, ExecutionException, IOException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, this.apiUrl());
        this.yahooJapanOAuthService.signRequest(accessToken, request);
        final Response response = this.yahooJapanOAuthService.execute(request);
        return response.getBody();
    }

    @GetMapping
    String redirect() {
        return "redirect:" + this.authUrl();
    }

    @GetMapping(path = "callback", params = "code")
    String callback(@RequestParam("code") final String code, final Model model) throws InterruptedException, ExecutionException, IOException {
        final OAuth2AccessToken accessToken = this.yahooJapanOAuthService.getAccessToken(code);
        model.addAttribute("response", this.user(accessToken));
        return "/response";
    }

    @GetMapping(path = "callback", params = {"error", "error_description"})
    String callbackError(
            @RequestParam("error") final String error,
            @RequestParam("error_description") final String errorDescription,
            final Model model) {
        model.addAttribute("error", MessageFormat.format("error: {0} / {1}", error, errorDescription));
        return "/error";
    }

}
