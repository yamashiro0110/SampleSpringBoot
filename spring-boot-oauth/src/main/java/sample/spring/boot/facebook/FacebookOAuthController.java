package sample.spring.boot.facebook;

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
import java.util.concurrent.ExecutionException;

/**
 * Created by yamashiro-r on 2017/03/13.
 */
@Controller
@RequestMapping("/oauth/facebook")
public class FacebookOAuthController {
    @Resource(name = "facebookOAuthService")
    private OAuth20Service oAuth20Service;

    private String api() {
        return "https://graph.facebook.com/v2.8/me?fields=id,name,email,first_name,last_name";
    }

    @GetMapping
    String redirect() {
        return "redirect:" + this.oAuth20Service.getAuthorizationUrl();
    }

    @GetMapping("callback")
    String callback(@RequestParam("code") String code, Model model) throws InterruptedException, ExecutionException, IOException {
        OAuth2AccessToken accessToken = this.oAuth20Service.getAccessToken(code);
        OAuthRequest request = new OAuthRequest(Verb.GET, this.api());
        this.oAuth20Service.signRequest(accessToken, request);
        Response response = this.oAuth20Service.execute(request);
        model.addAttribute("response", response.getBody());
        return "response";
    }

}
