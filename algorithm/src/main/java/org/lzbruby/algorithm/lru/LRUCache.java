package org.lzbruby.algorithm.lru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 功能描述：LRU缓存：双向链表+HASH
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：11:25
 */
public class LRUCache<K, V> {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LRUCache.class);

    /**
     * 缓存容器最大size
     */
    private int cacheCapcity;

    /**
     * 缓存容器
     */
    private HashMap<K, CacheNode> caches;

    /**
     * 缓存容器头
     */
    private CacheNode first;

    /**
     * 缓存容器尾
     */
    private CacheNode last;

    public LRUCache(int size) {
        this.cacheCapcity = size;
        this.caches = new HashMap<K, CacheNode>(size);
    }

    /**
     * 增加缓存，放在容器头， FIFO
     *
     * @param key key
     * @param val val
     */
    public void put(K key, V val) {
        // 查询缓存是否存在该缓存
        CacheNode vCacheNode = this.caches.get(key);
        if (vCacheNode == null) {
            // 队列长度超过长度, 删除链表最后节点
            if (this.caches.size() >= this.cacheCapcity) {
                this.caches.remove(this.last.hashKey);
                // 重新计算最后节点
                delLastNode();
            }

            // 创建新节点
            vCacheNode = new CacheNode(key);
        }

        // 存放到缓存到链条头
        vCacheNode.data = val;
        moveStartNode(vCacheNode);

        // 保存HASH
        caches.put(key, vCacheNode);
    }

    /**
     * 获取双向链表值
     *
     * @param key
     * @return
     */
    public V get(K key) {
        CacheNode node = caches.get(key);
        if (node == null)
            return null;

        // 命中索引移动到链表头
        moveStartNode(node);
        return node.data;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public V del(K key) {
        if (key == null)
            return null;

        CacheNode node = caches.get(key);
        if (node != null) {
            if (node.prev != null)
                node.prev.next = node.next;

            if (node.next != null)
                node.next.prev = node.prev;

            if (node == this.first)
                first = node.next;

            if (node == last)
                last = node.prev;

            CacheNode remove = caches.remove(key);
            return remove.data;
        }

        return null;
    }


    /**
     * 清除缓存
     */
    public void clear() {
        this.first = null;
        this.last = null;
        this.caches.clear();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        CacheNode node = this.first;
        while (node != null) {
            buffer.append(node.hashKey + ":" + node.data + " ");
            node = node.next;
        }

        return buffer.toString();
    }

    /**
     * 节点移动到双向链表表头
     *
     * @param node
     */
    private void moveStartNode(CacheNode node) {
        // 1.链表为空时，初始化同一个node为双向链表的头尾节点
        if (this.first == null || this.last == null) {
            this.first = node;
            this.last = node;
        }

        // 2.开始节点与node一致，直接返回，不需要操作
        if (this.first == node)
            return;

        // 3.如果节点是双向链表的尾
        if (node == this.last)
            this.last = node.prev;

        // 4.如果节点本身是双向链表中间的某的一个节点，
        // 1)该链表节点的prev前一个节点的next, 指向该节点next指向后一个节点的prev(与当前节点的next值一致)
        // 2)该链表节点的next后一个节点的prev, 指向该节点next指向前一个节点的next(与当前节点的prev值一致)
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;

        // 5.node与start交换
        node.next = this.first;
        this.first.prev = node;
        this.first = node;
        this.first.prev = null;
    }

    /**
     * 删除最后双向链表最后一个节点
     */
    private void delLastNode() {
        if (this.last != null) {
            this.last = this.last.prev;

            if (this.last == null) {
                this.last = null;
            } else {
                last.next = null;
            }
        }
    }

    /**
     * LRUCache Node
     */
    private class CacheNode {
        CacheNode prev;
        CacheNode next;
        K hashKey;
        V data;

        public CacheNode(K hashKey) {
            this.hashKey = hashKey;
        }

        @Override
        public String toString() {
            return "CacheNode{" +
                    "prev=" + prev +
                    ", next=" + next +
                    ", hashKey=" + hashKey +
                    ", data=" + data +
                    '}';
        }
    }
}
