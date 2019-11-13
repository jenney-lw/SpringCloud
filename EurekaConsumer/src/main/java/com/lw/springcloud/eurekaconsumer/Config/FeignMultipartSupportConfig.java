package com.lw.springcloud.eurekaconsumer.Config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

//@Configuration
public class FeignMultipartSupportConfig {

//    @Bean
//    @Primary
//    @Scope("prototype")
//    public Encoder multipartFormEncoder() {
//        return new SpringFormEncoder();
//    }

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }

}
