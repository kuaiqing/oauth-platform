package com.cxl.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cxl
 * @description 客户端属性配置
 * @date 2020/09/09 0009
 */
@Data
@Component
@ConfigurationProperties("system.client")
public class ClientLoadProperties {

    private ClientProperties[] clients = {};


}
