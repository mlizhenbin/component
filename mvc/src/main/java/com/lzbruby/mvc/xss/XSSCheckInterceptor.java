package com.lzbruby.mvc.xss;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 功能描述：WMS对XSS拦截
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/10/27 Time: 15:24
 */
public class XSSCheckInterceptor extends HandlerInterceptorAdapter {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XSSCheckInterceptor.class);

    /**
     * 执行XSS校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (null == request.getSession()) {
            LOGGER.info("request Session为空, 直接返回.");
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            XSSCheck annotation = method.getAnnotation(XSSCheck.class);

            if (annotation != null) {
                String[] params = annotation.params();
                LOGGER.info("XSSCheck url:" + request.getRequestURL());
                boolean isAttacked = isAttacked(request, params);
                if (isAttacked) {
                    LOGGER.info("XSS attacked URL:{}", request.getRequestURL());
                    LOGGER.info("XSS attacked IP:{}", getIp(request));

                    // 403(禁止)服务器拒绝请求
                    request.setAttribute("errCode", "403");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
                    dispatcher.forward(request, response);
                    return false;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 需要拦截的JS字符关键字
     */
    private static String[] safeless = {
            "<script",
            "</script",
            "<iframe",
            "</iframe",
            "<frame",
            "</frame",
            "set-cookie",
            "%3cscript",
            "%3c/script",
            "%3ciframe",
            "%3c/iframe",
            "%3cframe",
            "%3c/frame",
            "src=\"javascript:",
            "<body",
            "</body",
            "%3cbody",
            "%3c/body",
            "<",
            ">",
            "</",
            "/>",
            "%3c",
            "%3e",
            "%00",
            "%3c/",
            "/%3e",
            "'",
            "‘", "’"
    };

    /**
     * 校验是否包含
     *
     * @param value
     * @return
     */
    private static boolean isSafe(String value) {
        if (StringUtils.isNotEmpty(value)) {
            for (String s : safeless) {
                if (value.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean isAttacked(HttpServletRequest request, String[] params) {
        // path参数
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        // 验证request参数和path参数的安全性
        if (ArrayUtils.isNotEmpty(params)) {
            for (String param : params) {
                String requestValue = request.getParameter(param);
                String pathValue = null;
                if (pathVariables != null) {
                    pathValue = pathVariables.get(param);
                }

                if (!isSafe(requestValue) || !isSafe(pathValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
