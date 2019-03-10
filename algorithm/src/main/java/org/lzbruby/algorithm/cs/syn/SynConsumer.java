package org.lzbruby.algorithm.cs.syn;

import org.lzbruby.algorithm.cs.CS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：13:37
 */
public class SynConsumer implements Runnable {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SynConsumer.class);

    private String cName;
    private Queue<CS> queue;

    public SynConsumer(String cName, Queue<CS> queue) {
        this.cName = cName;
        this.queue = queue;
    }

    public void run() {
        while (true) {
            synchronized (this.queue) {
                while (this.queue.isEmpty()) {
                    LOGGER.info("queue empty, consumer thread wait. {}", Thread.currentThread().getName());
                    try {
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        LOGGER.error("", e);
                        throw new RuntimeException(e);
                    }
                }

                CS cs = this.queue.poll();
                cs.setcName(this.cName);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
                cs.setcDateTime(df.format(new Date()));

                LOGGER.info("Consumer CS = {}", cs);

                this.queue.notify();

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    LOGGER.error("", e);
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
