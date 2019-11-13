package com.lw.springcloud.eurekaconsumer.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //将RestClient接口代理实现注册为bean
        ClassLoader classLoader = annotationMetadata.getClass().getClassLoader();

        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableRestClients.class.getName());
        Class<?>[] clientClasses = attributes == null ? null : (Class[])((Class[])attributes.get("clients"));
//        Class<?>[] clientClasses = (Class<?>[]) attributes.get("clients");

        Stream.of(clientClasses)
                .filter(Class::isInterface) //仅选择接口
                .filter(interfaceClass -> {
                    return AnnotationUtils.findAnnotation(interfaceClass, RestClient.class) != null; //仅选标注@RestClient
                })
                .forEach(restClientClass -> {
                    RestClient restClient = AnnotationUtils.findAnnotation(restClientClass, RestClient.class);
                    String serviceName = restClient.name();

                    //过滤RequestMapping方法
                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restClientClass}, new RequestMappingMethodInvocationHandler(serviceName, beanFactory));

                    //注册bean
                    String beanName = "RestClient." + serviceName;

                    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);
                    //增加构造器参数
                    beanDefinitionBuilder.addConstructorArgValue(proxy);
                    beanDefinitionBuilder.addConstructorArgValue(restClientClass);

                    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                    beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);

                });

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    private static class RestClientClassFactoryBean implements FactoryBean {

        private final Object proxy;

        private final Class<?> restClientClass;

        private RestClientClassFactoryBean(Object proxy, Class<?> restClientClass) {
            this.proxy = proxy;
            this.restClientClass = restClientClass;
        }

        @Override
        public boolean isSingleton() {
            return false;
        }

        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }
}
