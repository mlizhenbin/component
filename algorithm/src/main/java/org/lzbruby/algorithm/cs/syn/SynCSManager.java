package org.lzbruby.algorithm.cs.syn;

import com.google.common.collect.Lists;
import org.lzbruby.algorithm.cs.CS;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：13:56
 */
public class SynCSManager {

    public static final int p_size = 5;
    public static final int c_size = 3;
    public static final int queue_size = 10;
    public static final Queue<CS> queue = Lists.newLinkedList();

    public static void main(String[] args) {

        ExecutorService pPool = Executors.newFixedThreadPool(p_size);
        for (int i = 0; i < p_size; i++) {
            pPool.execute(new Thread(new SynProducer("P" + i, queue, queue_size)));
        }

        ExecutorService cPool = Executors.newFixedThreadPool(c_size);
        for (int i = 0; i < c_size; i++) {
            cPool.execute(new Thread(new SynConsumer("C" + i, queue)));
        }

    }
}
