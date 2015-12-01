package com.lzbruby.core.direct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 功能描述: 获取配置字典加载器
 *
 * @author: lizhenbin
 * email: lizhenbin@oneplus.cn
 * company: 一加科技
 * Date: 2015/11/30 Time: 11:14:31
 */
@Service("configLoader")
public class ConfigLoader implements InitializingBean, ApplicationContextAware {

    /**
     * 上下文
     */
    private static ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        ConfigLoaderContext.getContext();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigLoader.setApplicationContext(applicationContext, null);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static void setApplicationContext(ApplicationContext applicationContext, Object args) {
        ConfigLoader.applicationContext = applicationContext;
    }
}
