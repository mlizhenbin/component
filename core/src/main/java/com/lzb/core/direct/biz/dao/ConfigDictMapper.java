package com.lzb.core.direct.biz.dao;

import com.lzb.core.direct.biz.dao.model.ConfigDict;

import java.util.List;
import java.util.Map;

/**
 * 功能描述: ConfigDict Dao Mapper
 *
 * @author: lizhenbin
 * email: lizhenbin@oneplus.cn
 * company: 一加科技
 * Date: 2015/11/30 Time: 11:14:31
 */
public interface ConfigDictMapper {

    /**
     * 创建ConfigDict
     *
     * @param configDict ConfigDict对象
     */
    public void addConfigDict(ConfigDict configDict);

    /**
     * 更新ConfigDict
     *
     * @param configDict ConfigDict对象
     */
    public void updateConfigDict(ConfigDict configDict);

    /**
     * 主键id删除ConfigDict
     *
     * @param id ConfigDict主键
     */
    public void deleteConfigDict(Long id);

    /**
     * 主键id查询ConfigDict
     *
     * @param id ConfigDict主键
     * @return ConfigDict对象
     */
    public ConfigDict findById(Long id);

    /**
     * 查询ConfigDict对象
     *
     * @param map 查询参数HashMap
     * @return ConfigDict对象
     */
    public ConfigDict findConfigDict(Map<String, Object> map);

    /**
     * 查询ConfigDict list集合
     *
     * @param map 查询参数HashMap
     * @return List<ConfigDict>对象
     */
    public List<ConfigDict> listConfigDict(Map<String, Object> map);

    /**
     * 查询ConfigDict数量
     *
     * @param map 查询参数HashMap
     * @return 查询记录条数
     */
    public Integer listConfigDictCount(Map<String, Object> map);

}
