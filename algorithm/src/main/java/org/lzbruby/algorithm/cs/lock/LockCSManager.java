package org.lzbruby.algorithm.cs.lock;

import com.google.common.collect.Lists;
import org.lzbruby.algorithm.cs.CS;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：13:56
 */
public class LockCSManager {

    public static final int p_size = 5;
    public static final int c_size = 3;
    public static final int queue_size = 10;
    public static final Queue<CS> queue = Lists.newLinkedList();

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        ExecutorService pPool = Executors.newFixedThreadPool(p_size);
        for (int i = 0; i < p_size; i++) {
            pPool.execute(new Thread(new LockProducer("P" + i, queue, queue_size, lock, condition)));
        }

        ExecutorService cPool = Executors.newFixedThreadPool(c_size);
        for (int i = 0; i < c_size; i++) {
            cPool.execute(new Thread(new LockConsumer("C" + i, queue, lock, condition)));
        }

    }
}
