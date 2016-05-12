package com.lzbruby.forward;


import com.lzbruby.core.forward.Forwarder;
import com.lzbruby.core.forward.impl.ForwardContext;
import com.lzbruby.core.forward.impl.ForwardType;

/**
 * facade分发模式测试
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/16 Time: 10:59
 */
public class ForwardTest {

    private Forwarder forwardFacade;

    public void testDefault() {
        ForwardContext context = new ForwardContext();
        context.setDistributeType(ForwardType.DEFAULT_DISTRIBUTE);
        forwardFacade.create(context);
    }

    public void testOversea() {
        ForwardContext context = new ForwardContext();
        context.setDistributeType(ForwardType.OVERSEA);
        forwardFacade.create(context);
    }


    public void testDomestic() {
        ForwardContext context = new ForwardContext();
        context.setDistributeType(ForwardType.DOMESTIC);
        forwardFacade.update(context);
    }
}
