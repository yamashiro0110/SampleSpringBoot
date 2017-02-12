package sample.spring.boot.yahoo.japan;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.Map;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
public class YahooJapanConnectApi20 extends DefaultApi20 {

    private static final YahooJapanConnectApi20 instance = new YahooJapanConnectApi20();

    public static YahooJapanConnectApi20 instance() {
        return instance;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://auth.login.yahoo.co.jp/yconnect/v1/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://auth.login.yahoo.co.jp/yconnect/v1/authorization";
    }

    @Override
    public String getAuthorizationUrl(final OAuthConfig config, final Map<String, String> additionalParams) {
        return super.getAuthorizationUrl(config, additionalParams);
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public OAuth20Service createService(final OAuthConfig config) {
        return new YahooJapanConnectOAuth20Service(this, config);
    }
}
