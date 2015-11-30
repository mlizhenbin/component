package com.lzb.core.direct.biz.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lzb.core.direct.biz.ConfigDictQueryService;
import com.lzb.core.direct.biz.dao.ConfigDictMapper;
import com.lzb.core.direct.biz.dao.model.ConfigDict;
import com.lzb.core.page.DefaultPaging;
import com.lzb.core.page.ListAdapterUtil;
import com.lzb.core.page.Paging;
import com.lzb.core.page.PagingRequest;
import com.lzb.core.page.adapter.ListAdapter;
import com.lzb.lang.CollectionUtil;
import com.lzb.lang.ListFieldConvertUtils;
import com.lzb.lang.QueryMapperUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Service("configDictQueryService")
public class ConfigDictQueryServiceImpl implements ConfigDictQueryService {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDictQueryServiceImpl.class);

    @Autowired
    private ConfigDictMapper configDictMapper;

    @Override
    public ConfigDict findConfigDictById(Long id) {
        if (id == null) {
            return null;
        }

        ConfigDict configDict = configDictMapper.findById(id);
        return configDict;
    }

    @Override
    public List<ConfigDict> listConfigDict(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }

        Map<String, Object> map = QueryMapperUtils.toQueryMap();
        map.put("ids", ids);
        List<ConfigDict> configDicts = configDictMapper.listConfigDict(map);
        return configDicts;
    }

    @Override
    public Map<Long, ConfigDict> mapConfigDict(Long[] ids) {
        List<ConfigDict> configDicts = listConfigDict(ids);
        if (CollectionUtil.isEmpty(configDicts)) {
            return Maps.newHashMap();
        }

        Map<Long, ConfigDict> configDictMap = ListFieldConvertUtils
                .getObjectMap(configDicts, "id");
        return configDictMap;
    }

    @Override
    public Paging<ConfigDict> findConfigDictByPage(ConfigDict configDict, PagingRequest pagingRequest) {
        // 查询请求参数转换
        ListAdapter<ConfigDict> listAdapter = ListAdapterUtil.<ConfigDict>convert2ListAdapter(pagingRequest);
        Map<String, Object> queryMap = listAdapter.convert(configDict);

        // 分页查询数量
        Integer count = configDictMapper.listConfigDictCount(queryMap);

        DefaultPaging<ConfigDict> paging = new DefaultPaging<ConfigDict>
                (pagingRequest.getPageNo(), pagingRequest.getPageSize(), count);
        if (count <= 0) {
            paging.setResult(new ArrayList<ConfigDict>(0));
            return paging;
        }

        List<ConfigDict> configDicts = configDictMapper.listConfigDict(queryMap);
        if (CollectionUtil.isEmpty(configDicts)) {
            paging.setResult(new ArrayList<ConfigDict>(0));
            return paging;
        }

        paging.setResult(configDicts);
        return paging;
    }

}