/**
 * okhqb.com.
 * Copyright (c) 2009-2013 All Rights Reserved.
 */
package com.lzbruby.core.sign;


import com.lzbruby.core.sign.util.KeyEnum;

import java.security.SignatureException;

/**
 * @author lizhenbin
 */
public interface Signature {

    public String sign(String content, KeyEnum keyEnum, String charset) throws SignatureException;

    public boolean verify(String content, KeyEnum keyEnum, String charset, String sign)
            throws SignatureException;

    public String sign(String content, String charset) throws SignatureException;

    public boolean verify(String content, String charset, String sign) throws SignatureException;

    public String sign(String content, String privateKey, String charset) throws SignatureException;

    public boolean verify(String content, String publicKey, String charset, String sign)
            throws SignatureException;

}
