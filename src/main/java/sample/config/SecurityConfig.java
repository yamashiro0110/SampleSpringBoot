package sample.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import sample.service.login.LoginUserDetailService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${request.auth.key}")
    private String authKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // '/login/form'へのリクエストは認証しない
                .antMatchers("/login/form", "/api/user/**")
                .permitAll()
                // それ以外のアクセスは認証が必要とする
                .anyRequest()
                .authenticated();

        http.formLogin()
                // login認証実行URL(?) Spring Securityがよしなにやってくれる
                .loginProcessingUrl("/login")
                // login画面のURL
                .loginPage("/login/form")
                // login失敗時のURL
                .failureUrl("/login/form?error")
                // login成功時に遷移するURL
                .defaultSuccessUrl("/user/list", true)
                // 認証時に使用するUserのIDのリクエストパラメータ名
                .usernameParameter("mail")
                // 認証時に使用するUserのPasswordのリクエストパラメータ名
                .passwordParameter("password");

        http.logout()
                // ログアウト時のURL
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                // ログアウト成功時に遷移するURL
                .logoutSuccessUrl("/login/form");

//        http.addFilterAfter(
//                new CustomFilter(new AntPathRequestMatcher("/api/**"), authKey),
//                AbstractAuthenticationProcessingFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 引数に指定したURLのパターンに一致するリクエストは認証しない
        web.ignoring().antMatchers(
                "/api/**",
                "/css/**",
                "/js/**",
                "/img/**");
    }

    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Resource
        private LoginUserDetailService loginUserDetailService;

        @Bean
        PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 'org.springframework.security.core.userdetails.UserDetailsService'を実装したクラスを設定
            auth.userDetailsService(loginUserDetailService)
                    // パスワードの暗号化を行うクラスを設定
                    .passwordEncoder(getPasswordEncoder());
        }
    }

    private class CustomFilter extends AbstractAuthenticationProcessingFilter {
        private String authKey;

        public CustomFilter(RequestMatcher requiresAuthenticationRequestMatcher, String authKey) {
            super(requiresAuthenticationRequestMatcher);
            this.authKey = authKey;
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
            final String authKey = request.getHeader("SAMPLE-AUTH-KEY");
            if (StringUtils.isEmpty(authKey)) throw new ServletException("http-header not contains 'SAMPLE-AUTH-KEY'");
            if (!StringUtils.equals(authKey, this.authKey)) throw new ServletException(String.format("doesn't match %s == %s", authKey, this.authKey));
            return SecurityContextHolder.getContext().getAuthentication();
        }
    }

}
