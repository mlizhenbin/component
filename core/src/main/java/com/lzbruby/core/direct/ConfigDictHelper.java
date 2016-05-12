package com.lzbruby.core.direct;

/**
 * 功能描述: 获取配置字典帮助类
 *
 * @author: lizhenbin
 * email: lizhenbin08@sina.cn
 * company: org.lzbruby
 * Date: 2015/11/30 Time: 11:14:31
 */
public class ConfigDictHelper {

    /**
     * 根据数据类型,获取KEY对应的VALUE
     * <p/>
     * <p>只匹配可见数据字典</p>
     *
     * @param configType 数据字典类型
     * @param key        数据类型KEY
     * @return VALUE
     */
    public static final String getValidValue(String configType, String key) {
        return ConfigLoaderContext.getContext().getValidValue(configType, key);
    }

    /**
     * 根据数据类型,获取KEY对应的VALUE
     * <p/>
     * <p>匹配可见和不可见数据字典</p>
     *
     * @param configType 数据字典类型
     * @param key        数据类型KEY
     * @return VALUE
     */
    public static final String getValue(String configType, String key) {
        return ConfigLoaderContext.getContext().getValue(configType, key);
    }

}
