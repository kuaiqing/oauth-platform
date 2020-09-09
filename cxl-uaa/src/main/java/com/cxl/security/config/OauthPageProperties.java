package com.cxl.security.config;

import lombok.Data;

/**
 * @author cxl
 * @description 权限页面的配置
 * @date 2020/09/09 0009
 */
@Data
public class OauthPageProperties {

    private String oauthLoginPage = "/oauthLogin";

    private String oauthGrantPage = "/oauthGrant";
}
