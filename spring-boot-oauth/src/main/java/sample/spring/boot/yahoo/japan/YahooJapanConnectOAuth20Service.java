package sample.spring.boot.yahoo.japan;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.text.MessageFormat;
import java.util.Base64;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
public class YahooJapanConnectOAuth20Service extends OAuth20Service {
    /**
     * Default constructor
     *
     * @param api    OAuth2.0 api information
     * @param config OAuth 2.0 configuration param object
     */
    public YahooJapanConnectOAuth20Service(final DefaultApi20 api, final OAuthConfig config) {
        super(api, config);
    }

    private String authorizationHeaderValue() {
        final String appId = super.getConfig().getApiKey();
        final String secret = super.getConfig().getApiSecret();
        final String authentication = MessageFormat.format("{0}:{1}", appId, secret);
        return Base64.getEncoder().encodeToString(authentication.getBytes());
    }

    @Override
    protected OAuthRequest createAccessTokenRequest(final String code) {
        final OAuthConfig config = super.getConfig();
        final OAuthRequest request = new OAuthRequest(super.getApi().getAccessTokenVerb(), super.getApi().getAccessTokenEndpoint());
        request.addHeader("Authorization", "Basic " + this.authorizationHeaderValue());
        request.addParameter(OAuthConstants.CODE, code);
        request.addParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
        return request;
    }
}
