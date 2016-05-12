package com.lzbruby.mvc;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Locale;

/**
 * 功能描述：Spring Message扩展
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 16/3/21 Time: 14:36
 */
public class MessageHelper implements MessageSourceAware {

    /**
     * 信息源
     */
    private MessageSource messageSource;

    /**
     * 是否转义HTML字符
     */
    private boolean defaultHtmlEscape = false;

    /**
     * Retrieve the message for the given code, using the "defaultHtmlEscape" setting.
     *
     * @param code code of the message
     * @return the message
     * @throws NoSuchMessageException if not found
     */
    public String getMessage(String code) throws NoSuchMessageException {
        return getMessage(code, null, isDefaultHtmlEscape());
    }

    /**
     * Retrieve the message for the given code, using the "defaultHtmlEscape" setting.
     *
     * @param code code of the message
     * @param args arguments for the message, or <code>null</code> if none
     * @return the message
     * @throws NoSuchMessageException if not found
     */
    public String getMessage(String code, Object[] args) throws NoSuchMessageException {
        return getMessage(code, args, isDefaultHtmlEscape());
    }

    /**
     * Retrieve the message for the given code, using the "defaultHtmlEscape" setting.
     *
     * @param code code of the message
     * @param args arguments for the message as a List, or <code>null</code> if none
     * @return the message
     * @throws NoSuchMessageException if not found
     */
    @SuppressWarnings("rawtypes")
    public String getMessage(String code, List args) throws NoSuchMessageException {
        return getMessage(code, (args != null ? args.toArray() : null), isDefaultHtmlEscape());
    }

    /**
     * Retrieve the message for the given code.
     *
     * @param code       code of the message
     * @param args       arguments for the message, or <code>null</code> if none
     * @param htmlEscape HTML escape the message?
     * @return the message
     * @throws NoSuchMessageException if not found
     */
    public String getMessage(String code, Object[] args, boolean htmlEscape)
            throws NoSuchMessageException {
        String msg = messageSource.getMessage(code, args, Locale.SIMPLIFIED_CHINESE);
        return (htmlEscape ? HtmlUtils.htmlEscape(msg) : msg);
    }

    /**
     * Retrieve the message for the given code.
     *
     * @param code       code of the message
     * @param args       arguments for the message, or <code>null</code> if none
     * @param htmlEscape HTML escape the message?
     * @param locale     本地化环境
     * @return the message
     * @throws NoSuchMessageException if not found
     */
    public String getMessage(String code, Object[] args, boolean htmlEscape, Locale locale)
            throws NoSuchMessageException {
        String msg = messageSource.getMessage(code, args,
                (null == locale ? Locale.SIMPLIFIED_CHINESE : locale));
        return (htmlEscape ? HtmlUtils.htmlEscape(msg) : msg);
    }

    /**
     * Retrieve the message for the given code, using the "defaultHtmlEscape" setting.
     *
     * @param code           code of the message
     * @param defaultMessage String to return if the lookup fails
     * @return the message
     */
    public String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage, isDefaultHtmlEscape());
    }

    /**
     * Retrieve the message for the given code, using the "defaultHtmlEscape" setting.
     *
     * @param code           code of the message
     * @param args           arguments for the message, or <code>null</code> if none
     * @param defaultMessage String to return if the lookup fails
     * @return the message
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        return getMessage(code, args, defaultMessage, isDefaultHtmlEscape());
    }

    /**
     * Retrieve the message for the given code, using the "defaultHtmlEscape" setting.
     *
     * @param code           code of the message
     * @param args           arguments for the message as a List, or <code>null</code> if none
     * @param defaultMessage String to return if the lookup fails
     * @return the message
     */
    @SuppressWarnings("rawtypes")
    public String getMessage(String code, List args, String defaultMessage) {
        return getMessage(code, (args != null ? args.toArray() : null), defaultMessage,
                isDefaultHtmlEscape());
    }

    /**
     * Retrieve the message for the given code.
     *
     * @param code           code of the message
     * @param args           arguments for the message, or <code>null</code> if none
     * @param defaultMessage String to return if the lookup fails
     * @param htmlEscape     HTML escape the message?
     * @return the message
     */
    public String getMessage(String code, Object[] args, String defaultMessage, boolean htmlEscape) {
        String msg = messageSource
                .getMessage(code, args, defaultMessage, Locale.SIMPLIFIED_CHINESE);
        return (htmlEscape ? HtmlUtils.htmlEscape(msg) : msg);
    }

    /**
     * Retrieve the message for the given code.
     *
     * @param code           code of the message
     * @param args           arguments for the message, or <code>null</code> if none
     * @param defaultMessage String to return if the lookup fails
     * @param htmlEscape     HTML escape the message?
     * @param locale         本地化环境
     * @return the message
     */
    public String getMessage(String code, Object[] args, String defaultMessage, boolean htmlEscape,
                             Locale locale) {
        String msg = messageSource.getMessage(code, args, defaultMessage, locale);
        return (htmlEscape ? HtmlUtils.htmlEscape(msg) : msg);
    }

    /**
     * Is default HTML escaping active?
     * Falls back to <code>false</code> in case of no explicit default given.
     */
    public boolean isDefaultHtmlEscape() {
        return defaultHtmlEscape;
    }

    public void setDefaultHtmlEscape(boolean defaultHtmlEscape) {
        this.defaultHtmlEscape = defaultHtmlEscape;
    }

    /**
     * @see MessageSourceAware#setMessageSource(MessageSource)
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
