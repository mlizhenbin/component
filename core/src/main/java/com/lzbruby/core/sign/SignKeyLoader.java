/**
 * okhqb.com.
 * Copyright (c) 2009-2013 All Rights Reserved.
 */
package com.lzbruby.core.sign;


import com.lzbruby.core.sign.util.KeyEnum;

/**
 * 密钥加载器
 *
 * @author lizhenbin
 */
public interface SignKeyLoader {

    /**
     * 获取指定密钥值的秘钥
     *
     * @param keyEnum 指定密钥值
     * @return 密钥
     */
    public String getKey(KeyEnum keyEnum);

}
