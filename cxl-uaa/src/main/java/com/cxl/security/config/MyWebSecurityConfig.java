package com.cxl.security.config;

import com.cxl.security.handler.MyAuthenctiationSuccessHandler;
import com.cxl.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author cxl
 * @description WebSecurity处理逻辑
 * @date 2020/09/09 0009
 */
@Configuration
@EnableWebSecurity
@Order(-111)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    com.cxl.security.service.MyUserDetailService myUserDetailService;
    @Autowired
     @Qualifier("myAuthenctiationSuccessHandler")
    com.cxl.security.handler.MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
    @Autowired
    @Qualifier("myAuthenctiationFailureHandler")
    com.cxl.security.handler.MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "authenticationManagerBean")
    public AuthenticationManager AuthenticationManagerBean() throws Exception{

        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .successHandler(myAuthenctiationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
//                .loginPage("/login")
                .and().httpBasic().and()
                .authorizeRequests().antMatchers(
                        "/user/helloword",
                securityProperties.getOauthLogin().getOauthLoginPage(),
                securityProperties.getOauthLogin().getOauthGrantPage()
        ).permitAll().anyRequest().authenticated()
                .and().csrf().disable();
    }
}
