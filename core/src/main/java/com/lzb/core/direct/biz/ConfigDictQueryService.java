package com.lzb.core.direct.biz;


import com.lzb.core.direct.biz.dao.model.ConfigDict;
import com.lzb.core.page.Paging;
import com.lzb.core.page.PagingRequest;

import java.util.List;
import java.util.Map;

/**
 * 功能描述: ConfigDict Service
 *
 * @author: lizhenbin
 * email: lizhenbin@oneplus.cn
 * company: 一加科技
 * Date: 2015/11/30 Time: 11:14:31
 */
public interface ConfigDictQueryService {

    /**
     * 通过主键Long查询ConfigDict
     *
     * @param id ConfigDict主键
     * @return ConfigDict对象
     */
    public ConfigDict findConfigDictById(Long id);

    /**
     * 通过Long集合查询ConfigDict
     *
     * @param ids ConfigDicts主键数组
     * @return List<ConfigDict>
     */
    public List<ConfigDict> listConfigDict(Long[] ids);

    /**
     * 通过Long集合查询ConfigDict
     *
     * @param ids ConfigDicts主键数组
     * @return Map<Long, ConfigDict> KEY:id Value:ConfigDict
     */
    public Map<Long, ConfigDict> mapConfigDict(Long[] ids);

    /**
     * 分页查询ConfigDict
     *
     * @param configDict    configDict对象
     * @param pagingRequest 分页请求
     * @return paging对象
     */
    public Paging<ConfigDict> findConfigDictByPage(ConfigDict configDict, PagingRequest pagingRequest);

}