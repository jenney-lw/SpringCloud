package com.lw.springcloud.eurekaconsumer.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestClient {

    //REST服务名称
    String name();

}
