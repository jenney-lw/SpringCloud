package com.lw.springcloud.eurekaconsumer.annotation;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RestClientsRegistrar.class})
public @interface EnableRestClients {

    //指定RestClient接口
    Class<?>[] clients() default {};

}
