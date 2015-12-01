package com.lzbruby.core.page;

import com.lzbruby.lang.NoNullStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 功能描述：分页查询对象
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/9/9 Time: 18:26
 */
public class PagingVO {

    /**
     * 当前页
     */
    private int pageNo = 1;

    /**
     * 每页条数
     */
    private int pageSize = 20;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, new NoNullStyle());
    }
}
