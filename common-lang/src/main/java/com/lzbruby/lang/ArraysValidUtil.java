package com.lzbruby.lang;

import com.google.common.collect.Sets;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * 功能描述：数组元素验证工具类
 *
 * @author: Zhenbin.Li
 * email： zhenbin.li@okhqb.com
 * company：华强北在线
 * Date: 13-11-3 Time：下午12:29
 */
public class ArraysValidUtil {

    /**
     * 验证传入的对象数组参数长度是否为一致
     * <p/>
     * <p>多个数组，验证多个数组长度要一致</p>
     *
     * @param objects 数组集合
     * @return
     */
    public static <T> boolean isSameLength(T[]... objects) {

        if (ArrayUtils.isEmpty(objects)) {
            return false;
        }

        Set<Integer> arrayLengths = Sets.newHashSet();
        for (Object[] array : objects) {

            if (ArrayUtils.isEmpty(array)) {
                return false;
            }
            arrayLengths.add(array.length);
        }

        if (arrayLengths.size() != 1) {
            return false;
        }

        return true;
    }

    /**
     * 验证传入的对象数组参数长度是否为一致，并且数组里面的值不能为空
     * <p/>
     * <p>多个数组，验证多个数组长度要一致</p>
     * <p>数组的值不能为空</p>
     *
     * @param objects 数组集合
     * @return
     */
    public static <T> boolean isSameLengthAndElementsNotEmpty(T[]... objects) {
        if (!isSameLength(objects)) {
            return false;
        }

        for (Object[] array : objects) {
            for (Object obj : array) {

                if (obj == null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 验证传入的对象数组参数长度是否为一致，并且数组里面的值不能为空
     * String类型不能为Blank
     * <p/>
     * <p>多个数组，验证多个数组长度要一致</p>
     * <p>数组的值不能为空</p>
     *
     * @param objects 数组集合
     * @return
     */
    public static <T> boolean isSameLengthAndElementsNotBlank(T[]... objects) {
        if (!isSameLength(objects)) {
            return false;
        }

        for (Object[] array : objects) {
            for (Object obj : array) {

                if (obj == null) {
                    return false;
                }

                if (obj instanceof String) {
                    if (StringUtils.isBlank(obj.toString())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
