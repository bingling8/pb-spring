package pb.spring.test;

import pb.springframwork.annotation.Component;
import pb.springframwork.context.BeanPostProcessor;

import java.lang.reflect.Proxy;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean){
        if (("userService").equals(beanName)) {
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean){
        if (("userService").equals(beanName)) {
            Object ProxyInstance = Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.println("切面逻辑");
                return method.invoke(bean, args);
            });

            return ProxyInstance;

        }
        return bean;
    }
}
