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
public class SynProducer implements Runnable {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SynProducer.class);

    private String pName;
    private Queue<CS> queue;
    private int size;

    public SynProducer(String pName, Queue<CS> queue, int size) {
        this.pName = pName;
        this.queue = queue;
        this.size = size;
    }

    public void run() {
        while (true) {
            synchronized (this.queue) {
                while (this.queue.size() >= this.size) {
                    try {
                        LOGGER.info("queue full, product thread wait. {}", Thread.currentThread().getName());
                        this.queue.wait();
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

                this.queue.notify();

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    LOGGER.error("", e);
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
