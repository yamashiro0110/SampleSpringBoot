package sample.spring.boot.google;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by yamashiro-r on 2017/02/11.
 */
@Controller
@RequestMapping("/oauth/google")
public class GoogleOAuthController {
    @Resource(name = "googleOAuthService")
    private OAuth20Service googleOAuthService;

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
        this.googleOAuthService.signRequest(accessToken, request);
        final Response response = this.googleOAuthService.execute(request);
        return response.getBody();
    }

    @GetMapping
    String redirect() {
        return "redirect:" + this.googleOAuthService.getAuthorizationUrl(this.apiParams());
    }

    @GetMapping("callback")
    String callback(@RequestParam("code") final String code, final Model model) throws InterruptedException, ExecutionException, IOException {
        final OAuth2AccessToken accessToken = this.googleOAuthService.getAccessToken(code);

        if (StringUtils.isEmpty(accessToken.getRefreshToken())) {
            model.addAttribute("response", this.profile(accessToken));
            return "/response";
        }

        final OAuth2AccessToken refreshToken = this.googleOAuthService.refreshAccessToken(accessToken.getRefreshToken());
        model.addAttribute("response", this.profile(refreshToken));
        return "/response";
    }
}
