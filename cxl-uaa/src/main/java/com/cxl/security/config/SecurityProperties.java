package com.cxl.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cxl
 * @description 验证实体类
 * @date 2020/09/09 0009
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SecurityProperties {

    private OauthPageProperties oauthLogin = new OauthPageProperties();
}
