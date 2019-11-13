package com.lw.springcloud.eurekaconsumer.annotation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestMappingMethodInvocationHandler implements InvocationHandler {

    private final String serviceName;

    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final BeanFactory beanFactory;

    public RequestMappingMethodInvocationHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //过滤@RequestMapping
        GetMapping requestMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);

        if (requestMapping != null) {
            //得到URI
            String[] uri = requestMapping.value();
            //获取方法参数的数量
            int paramCount = method.getParameterCount();
            //获取有序的方法参数
            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
            //获取方法参数类型
            Class<?>[] paramTypes = method.getParameterTypes();
            //获取方法注解集合
            Annotation[] annotations = method.getDeclaredAnnotations();

            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("http://").append(serviceName).append(uri[0]);

            StringBuilder queryStringBuilder = new StringBuilder();

            for (int i = 0 ; i < paramCount; i++) {
                Class<?> paramType = paramTypes[i];

                RequestParam requestParam = paramType.getAnnotation(RequestParam.class);
                if (requestParam != null) {
                    String paramName = paramNames[i];

                    //组合HTTP请求参数
                    String requestParamName = StringUtils.hasText(requestParam.value()) ? requestParam.value() : paramName;
                    String requestParamValue = String.class.equals(paramType) ? (String) args[i] : String.valueOf(args[i]);

                    queryStringBuilder.append("&").append(requestParamName).append("=").append(requestParamValue);
                }
            }

            String queryString = queryStringBuilder.toString();
            if (StringUtils.hasText(queryString)) {
                urlBuilder.append("?").append(queryString);
            }
            String url = urlBuilder.toString();

            //RestTemplate执行
            RestTemplate restTemplate = beanFactory.getBean("restTemplate", RestTemplate.class);
            return restTemplate.getForObject(url, method.getReturnType());

        }
        return null;
    }

}
