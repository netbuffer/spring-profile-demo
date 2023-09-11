package cn.netbuffer.spring.profile.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${app.name:null}")
    private String appName;
    @Value("${sys.name:null}")
    private String sysName;
    @Value("${order.name:null}")
    private String orderName;

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

}
