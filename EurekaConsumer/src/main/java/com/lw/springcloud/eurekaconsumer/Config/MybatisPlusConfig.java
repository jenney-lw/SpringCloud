package com.lw.springcloud.eurekaconsumer.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:/mybatis/spring-mybatis.xml"})
public class MybatisPlusConfig {
}
