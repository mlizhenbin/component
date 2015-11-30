package com.lzb.core.direct;

import com.lzb.core.direct.biz.ConfigDictQueryService;
import com.lzb.core.direct.biz.dao.model.ConfigDict;
import com.lzb.core.page.Paging;
import com.lzb.core.page.PagingRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 功能描述: 获取配置字典上下文
 *
 * @author: lizhenbin
 * email: lizhenbin@oneplus.cn
 * company: 一加科技
 * Date: 2015/11/30 Time: 11:14:31
 */
public class ConfigLoaderContext {

    /**
     * LOG
     */
    private static final Logger LOG = LoggerFactory.getLogger(ConfigLoaderContext.class);

    /**
     * 单实例.
     */
    private static ConfigLoaderContext context = null;

    /**
     * CodeType和key的连接符号.
     */
    private static final String MAGIC_SEPERATOR = "@#$%^&*";

    /**
     * 刷新时间, 默认为1小时刷新一次
     */
    private static long refreshTime = 1 * 60 * 60 * 1000;

    /**
     * 最后刷新时间
     */
    private static long lastRefreshTime = 0;

    /**
     * 存放配置session.
     */
    private Map<String, Object> session = new HashMap<String, Object>();

    /**
     * 单例模式.
     *
     * @return 接口
     */
    public static ConfigLoaderContext getContext() {
        synchronized (ConfigLoaderContext.class) {
            if (context == null) {
                context = new ConfigLoaderContext();
            }
        }

        long currentTime = System.currentTimeMillis();
        // 如果超过刷新时间，则刷新数据字典
        try {
            if ((currentTime - lastRefreshTime) > refreshTime) {
                LOG.info("正在更新配置信息(更新时间:" + new Date(currentTime) + ")");

                context.refresh();

                // 更新最后刷新时间
                lastRefreshTime = System.currentTimeMillis();
                LOG.info("更新配置信息完毕！");
            }
        } catch (Exception e) {
            LOG.error("更新数据字典出错，出错原因:", e);
            context = null;
        }

        return context;
    }

    /**
     * 私有构造.
     */
    private ConfigLoaderContext() {
        // 第一次创建类时最后刷新时间为当前时间
        lastRefreshTime = System.currentTimeMillis();
        refresh();
    }

    /**
     * 设置刷新时间，使用者可以通过此接口改变默认的刷新时间。
     *
     * @param refreshTime
     */
    public static void setRefreshTime(long refreshTime) {
        ConfigLoaderContext.refreshTime = refreshTime;
    }

    /**
     * 得到数据字典DO.
     *
     * @param configType 编码类型
     * @param key        编码key
     * @return 数据字典DO
     */
    public ConfigDict getCodeDict(String configType, String key) {
        if (configType == null || configType.length() == 0
                || key == null || key.length() == 0) {
            return null;
        }

        // 以configType + MAGIC_SEPERATOR + key做为HashMap的key键
        String magicKey = getMagicKey(configType, key);
        return (ConfigDict) session.get(magicKey);
    }

    /**
     * 得到全部数据字典DO列表（可见+不可见）.
     *
     * @param configType 编码类型
     * @return 数据字典DO列表
     */
    @SuppressWarnings("unchecked")
    public List<ConfigDict> getCodeDicts(String configType) {
        if (configType == null || configType.length() == 0) {
            return null;
        }
        return (List<ConfigDict>) session.get(configType);
    }

    /**
     * 得到有效数据字典DO列表.
     *
     * @param configType 编码类型
     * @return 数据字典DO列表
     */
    @SuppressWarnings("unchecked")
    public List<ConfigDict> getValidCodeDicts(String configType) {
        if (configType == null || configType.length() == 0) {
            return null;
        }

        // 过滤掉无效的数据字典（前台下拉框不可见的）
        List<ConfigDict> configDicts = (List<ConfigDict>) session.get(configType);
        if (configDicts == null || configDicts.size() == 0) {
            return new ArrayList<ConfigDict>();
        }

        // 把过滤之后的放入另外一个list中返回
        List<ConfigDict> validList = new ArrayList<ConfigDict>();
        for (ConfigDict configDictDo : configDicts) {
            if (!ConfigDict.VALID_FALG_NO.equals(configDictDo.getValidFlag())) {
                validList.add(configDictDo);
            }
        }
        return validList;
    }

    /**
     * 根据数据类型,获得KEY最大值
     * <p/>
     * 注意：该情况只适合定义的VALUE是数字
     *
     * @param configType 数据字典类型
     * @return Integer
     */
    public Integer getMaxIntegerKey(String configType) {
        if (StringUtils.isEmpty(configType)) {
            return null;
        }
        List<ConfigDict> listCodeDictDo = ConfigLoaderContext.getContext().getCodeDicts(configType);
        if (CollectionUtils.isEmpty(listCodeDictDo)) {
            return null;
        }
        Integer returnVal = null;
        for (ConfigDict configDictDo : listCodeDictDo) {
            String key = configDictDo.getKey();
            if (StringUtils.isNotEmpty(key)) {
                Integer tempVal = Integer.valueOf(key);
                if (null == returnVal) {
                    returnVal = tempVal;
                } else if (null != returnVal && returnVal < tempVal) {
                    returnVal = tempVal;
                }
            }
        }
        return returnVal;
    }

    /**
     * 根据数据类型,获取KEY对应的VALUE
     * <p/>
     * <p>只匹配可见数据字典</p>
     *
     * @param configType 数据字典类型
     * @param key        数据类型KEY
     * @return VALUE
     */
    public final String getValidValue(String configType, String key) {
        String returnKey = "";
        if (StringUtils.isEmpty(configType) || StringUtils.isEmpty(key)) {
            return returnKey;
        }
        List<ConfigDict> listCodeDictDo = ConfigLoaderContext.getContext().getValidCodeDicts(configType);
        returnKey = getValueByKey(listCodeDictDo, key);

        return returnKey;
    }

    /**
     * 根据KEY找出匹配VALUE
     *
     * @param listCodeDictDo 数据字典集合
     * @param key            值
     * @param key            键
     */
    private String getValueByKey(List<ConfigDict> listCodeDictDo, String key) {
        String returnKey = "";
        for (ConfigDict configDictDo : listCodeDictDo) {
            String keyInDo = configDictDo.getKey();
            if (key.equals(keyInDo)) {
                returnKey = configDictDo.getValue();
                break;
            }
        }
        return returnKey;
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
    public final String getValue(String configType, String key) {
        String returnKey = "";
        if (StringUtils.isEmpty(configType) || StringUtils.isEmpty(key)) {
            return returnKey;
        }
        List<ConfigDict> listCodeDictDo = ConfigLoaderContext.getContext().getCodeDicts(configType);
        return getValueByKey(listCodeDictDo, key);
    }

    public void reload() {
        refresh();
    }

    /**
     * 刷新数据字典.
     */
    @SuppressWarnings("unchecked")
    private void refresh() {
        ConfigDictQueryService configDictBiz = (ConfigDictQueryService) ConfigLoader.getBean("configDictService");

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setPageSize(Integer.MAX_VALUE);
        Paging<ConfigDict> paging = configDictBiz.findConfigDictByPage(new ConfigDict(), pagingRequest);
        if (paging == null || CollectionUtils.isEmpty(paging.getResult())) {
            LOG.debug("ConfigDict从DB读取配置信息为空");
            return;
        }

        List<ConfigDict> configDicts = paging.getResult();
        LOG.debug("ConfigDict从DB读取配置信息.");
        for (ConfigDict configDict : configDicts) {
            LOG.debug("config=" + configDict);
        }

        // 将数据构造到tmp的HashMap中
        Map<String, Object> tmpMap = new HashMap<String, Object>();

        // 循环处理所有CodeDict列表
        for (Iterator<?> it = configDicts.iterator(); it.hasNext(); ) {
            ConfigDict configDictDo = (ConfigDict) it.next();
            List<ConfigDict> configTypeList = (List<ConfigDict>) tmpMap.get(configDictDo.getConfigType());

            // 将所有的configType做为key保存列表
            if (configTypeList == null) {
                configTypeList = new ArrayList<ConfigDict>();
                configTypeList.add(configDictDo);
                tmpMap.put(configDictDo.getConfigType(), configTypeList);
            } else {
                configTypeList.add(configDictDo);
            }

            // 以configType + MAGIC_SEPERATOR + key做为HashMap的key键
            String magicKey = getMagicKey(configDictDo.getConfigType(), configDictDo.getKey());
            tmpMap.put(magicKey, configDictDo);
        }

        // 替换现有的map数据.
        Map<String, Object> oldMap = session;
        session = tmpMap;
        oldMap.clear();
    }

    /**
     * 得到configType + MAGIC_SEPERATOR + key
     *
     * @param configType 编码
     * @param key        键
     * @return magic键
     */
    private String getMagicKey(String configType, String key) {
        return configType + MAGIC_SEPERATOR + key;
    }
}
