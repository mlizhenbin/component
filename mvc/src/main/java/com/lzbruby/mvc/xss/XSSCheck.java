package com.lzbruby.mvc.xss;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 功能描述：WEB XSS处理
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/10/27 Time: 15:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XSSCheck {

    /**
     * 校验URL参数
     *
     * @return
     */
    String[] params() default {};
}
