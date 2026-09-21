package cn.netbuffer.spring.profile.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${app.name:null}")
    private String appName;
    @Value("${app.isTest:true}")
    private Boolean isTest;
    @Value("${sys.name:sys-name-default}")
    private String sysName;
    @Value("${order.name:null}")
    private String orderName;
    @Value("${order.detail:null}")
    private String orderDetail;
    @Value("${app.info:null}")
    private String appInfo;
    @Value("${ai.model.openai.api-key:null}")
    private String openAIApiKey;
    @Resource
    private Environment environment;

    @GetMapping("isTest")
    public Boolean getIsTest() {
        return isTest;
    }

    @GetMapping("appName")
    public String getAppName() {
        return appName;
    }

    @GetMapping("sysName")
    public String getSysName() {
        return sysName;
    }

    @GetMapping("orderName")
    public String getOrderName() {
        return orderName;
    }

    @GetMapping("orderDetail")
    public String getOrderDetail() {
        return orderDetail;
    }

    @GetMapping("appInfo")
    public JSONObject getAppInfo() {
        return JSON.parseObject(appInfo);
    }

    @GetMapping("openAIApiKey")
    public String getOpenAIApiKey() {
        return openAIApiKey;
    }

    @GetMapping("getProperty")
    public String getProperty(String key) {
        return environment.getProperty(key);
    }

}
