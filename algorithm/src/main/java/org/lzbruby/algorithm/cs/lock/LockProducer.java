package org.lzbruby.algorithm.cs.lock;

import org.lzbruby.algorithm.cs.CS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：13:37
 */
public class LockProducer implements Runnable {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LockProducer.class);

    private String pName;
    private Queue<CS> queue;
    private int size;
    private Lock lock;
    private Condition condition;

    public LockProducer(String pName, Queue<CS> queue, int size, Lock lock, Condition condition) {
        this.pName = pName;
        this.queue = queue;
        this.size = size;
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        while (true) {
            try {
                this.lock.lock();

                while (this.queue.size() >= this.size) {
                    try {
                        LOGGER.info("queue full, product thread wait. {}", Thread.currentThread().getName());
                        this.condition.await();
                    } catch (InterruptedException e) {
                        LOGGER.error("", e);
                        throw new RuntimeException(e);
                    }
                }

                CS cs = new CS("CS DATA");
                cs.setpName(this.pName);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
                cs.setpDateTime(df.format(new Date()));

                this.queue.offer(cs);

                LOGGER.info("Produce CS = {}", cs);

                this.condition.signal();

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    LOGGER.error("", e);
                    throw new RuntimeException(e);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
