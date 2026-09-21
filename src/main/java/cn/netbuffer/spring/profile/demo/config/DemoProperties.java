package cn.netbuffer.spring.profile.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.demo")
public class DemoProperties {
    private String serverPort; // 驼峰字段
}