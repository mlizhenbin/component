package com.lzbruby.lang;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 功能描述：XStream工具类，将XML与JAVA对象之间互相转换工具类
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/30 Time: 10:31
 */
public class XStreamUtils {

    private XStreamUtils() {
    }

    /**
     * 对象直接转换为XML字符串格式
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toXml(T t) {
        XStream xstream = new XStream();
        xstream.processAnnotations(t.getClass());
        String srcXML = xstream.toXML(t);
        return srcXML;
    }

    /**
     * XML直接转化为对象
     *
     * @param xml
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String xml, Class<T> clazz) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(clazz);
        T obj = (T) xstream.fromXML(xml);
        return obj;
    }
}
