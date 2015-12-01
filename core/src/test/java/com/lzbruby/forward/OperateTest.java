package com.lzbruby.forward;

import com.lzbruby.core.forward.Operator;
import com.lzbruby.core.forward.business.DefaultOperateService;
import com.lzbruby.core.forward.business.OverseaOperateService;
import com.lzbruby.core.forward.impl.OperateContext;
import com.lzbruby.core.forward.impl.OperateType;
import com.lzbruby.core.forward.impl.OperatorBeanManager;

/**
 * 功能描述：调用测试描述
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/16 Time: 09:55
 */
public class OperateTest {


    public void testCommon() throws Exception {
        Operator<OperateContext, ?> operator = (Operator<OperateContext, ?>)
                getObject(OperateType.DEFAULT_OPERATE);
        OperateContext context = new OperateContext();
        operator.create(context);
    }

    public void testDefault() throws Exception {
        DefaultOperateService<? extends OperateContext, ?> operator = (DefaultOperateService<? extends OperateContext, ?>)
                getObject(OperateType.DEFAULT_OPERATE);
        OperateContext.DefaultContext defaultContext = new OperateContext.DefaultContext();
        operator.create(defaultContext);
    }

    public void testOverSea() throws Exception {
        OverseaOperateService<? extends OperateContext, ?> operator = (OverseaOperateService<? extends OperateContext, ?>)
                getObject(OperateType.OVERSEA);
        OperateContext.OverseaContext context = new OperateContext.OverseaContext();
        operator.create(context);
        operator.listOverseaCountiesByCodes(new String[]{"orderNo"});
    }

    // 初始化对象
    public Operator<? extends OperateContext, ?> getObject(OperateType operateType) throws Exception {
        return OperatorBeanManager.getOperatorByType(operateType);
    }

}
