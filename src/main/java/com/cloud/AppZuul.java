package com.cloud;

import com.cloud.interrupter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: AppZuul
 * @Auther: Administrator
 * @Date: 2019/7/26 0026 17:53
 *
 * 添加 配置中心，一部分配置在 ConfigCenter中，如需添加配置或者修改配置，可在 ConfigCenter -> app-com.cloud-zuul-dev.yml 中配置
 * 配置完 需要调用接口刷新到最新配置 http://127.0.0.1:8000/actuator/refresh
 *
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppZuul {

    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class,args);
    }

    //将过滤器交给spring管理
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}
