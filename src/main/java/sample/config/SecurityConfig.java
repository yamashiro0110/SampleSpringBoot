package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sample.login.service.LoginUserDetailService;

import javax.annotation.Resource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSFRを無効
        http.csrf().disable();

        http.authorizeRequests()
                // '/login/form'へのリクエストは認証しない
                .antMatchers("/login/form").permitAll()
                // それ以外のアクセスは認証が必要とする
                .anyRequest().authenticated();

        http.formLogin()
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
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 引数に指定したURLのパターンに一致するリクエストは認証しない
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
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
}
