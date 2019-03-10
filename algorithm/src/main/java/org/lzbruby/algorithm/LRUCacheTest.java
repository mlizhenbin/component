package org.lzbruby.algorithm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：13:25
 */
public class LRUCacheTest {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LRUCacheTest.class);

    @Test
    public void testLRUCache() {
        LRUCache<Integer, String> cache = new LRUCache<Integer, String>(5);
        cache.put(1, "a");
        LOGGER.info("cache.put(1, \"a\")-->{}", cache);
        cache.put(2, "b");
        LOGGER.info("cache.put(2, \"b\")-->{}", cache);
        cache.put(3, "c");
        LOGGER.info("cache.put(3, \"c\")-->{}", cache);
        cache.get(2);
        LOGGER.info("cache.get(2)-->{}", cache);
        cache.put(4, "d");
        LOGGER.info("cache.put(4, \"d\")-->{}", cache);
        cache.put(5, "e");
        LOGGER.info("cache.put(5, \"e\")-->{}", cache);
        cache.get(2);
        LOGGER.info("cache.get(2)-->{}", cache);
        cache.put(6, "f");
        LOGGER.info("cache.put(6, \"f\")-->{}", cache);
        cache.del(1);
        LOGGER.info("cache.del(1)-->{}", cache);
        cache.put(1, "a");
        LOGGER.info("cache.put(1, \"a\")-->{}", cache);
    }
}
