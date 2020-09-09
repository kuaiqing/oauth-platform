package com.cxl.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cxl
 * @description 允许请求的方式
 * @date 2020/09/09 0009
 */
@Configuration
public class AllowedMethodConfig {
    @Resource
    private TokenEndpoint tokenEndpoint;

    @PostConstruct
    public void reconfigure(){
        Set<HttpMethod> allowedMethod = new HashSet<>(Arrays.asList(HttpMethod.GET,HttpMethod.POST));
        tokenEndpoint.setAllowedRequestMethods(allowedMethod);
    }
}
