package com.cxl.security.config;

import com.cxl.security.service.MyUserDetailService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author cxl
 * @description 认证服务
 * @date 2020/09/09 0009
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager  authenticationManager;
    @Resource
    private com.cxl.security.service.MyUserDetailService MyUserDetailService;
    @Resource
    private RedisConnectionFactory connectionFactory;
    @Resource
    private ClientLoadProperties clientLoadProperties;

    //token有效期
    private static final int accessTokenValiditySeconds = 7*24*3600;
    //刷新token时间
    private static final int refreshTokenValiditySeconds = 7*24*3600;

    /*
    定义token存取方式
     */
    @Bean
    public TokenStore tokenStore(){
        return new RedisTokenStore(connectionFactory);
    }

    /*
    允许的安全认证方式
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticates()");
        security.allowFormAuthenticationForClients();
    }
    /*
    设置认证方式及支持的Token存储时间:
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder s = clients.inMemory();
        ClientProperties[] clients1 = clientLoadProperties.getClients();
        if(ArrayUtils.isNotEmpty(clients1)){
            for (ClientProperties config:clients1) {
                //设置客户端 - 密码
                s.withClient(config.getClientId()).secret(config.getClientSecret())
                 //设置token有效期
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                 //设置刷新token有效期时间
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
                  //支持认证方式
                .authorizedGrantTypes("refresh_token","authorization_code","password").autoApprove(false)
                 //跳转页面
                 .redirectUris(config.getClientRedirectUri())
                 //授权域
                .scopes("all");
            }
        }
    }

    /*
    令牌处理服务器:
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //认证管理器
                .authenticationManager(authenticationManager)
                //用户账号认证
                .userDetailsService(MyUserDetailService)
                //刷新token
                .reuseRefreshTokens(false)
                //指定token储存位置
                .tokenStore(tokenStore());
    }
}
