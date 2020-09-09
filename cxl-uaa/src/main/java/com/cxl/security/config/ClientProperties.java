package com.cxl.security.config;

import lombok.Data;

/**
 * @author cxl
 * @description 客户端id和密钥配置
 * @date 2020/09/09 0009
 */
@Data
public class ClientProperties {
    /*
    授权客户端id
     */
    private String clientId;
    /*
    授权客户端密钥
     */
    private String clientSecret;
    /*
    授权成功跳转路径
     */
    private String clientRedirectUri;
}
