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
public class LockConsumer implements Runnable {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LockConsumer.class);

    private String cName;
    private Queue<CS> queue;
    private Lock lock;
    private Condition condition;

    public LockConsumer(String cName, Queue<CS> queue, Lock lock, Condition condition) {
        this.cName = cName;
        this.queue = queue;
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        while (true) {
            try {
                lock.lock();

                while (this.queue.isEmpty()) {
                    try {
                        this.condition.await();
                    } catch (InterruptedException e) {
                        LOGGER.error("", e);
                        throw new RuntimeException(e);
                    }
                    LOGGER.info("queue empty, consumer thread wait. {}", Thread.currentThread().getName());
                }

                CS cs = this.queue.poll();
                cs.setcName(this.cName);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
                cs.setcDateTime(df.format(new Date()));

                LOGGER.info("Consumer CS = {}", cs);

                this.condition.signal();

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
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
