package com.voc.api.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/30 16:58
 */
public class RequestUtil {

    private static final String AJAX_HEADER_NAME = "x-requested-with";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    public static Pattern REST_JSON_PATTERN = Pattern.compile("application/json.*", Pattern.CASE_INSENSITIVE);

    /**
     * 是否是 restful 请求
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isRestfulRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (!StringUtils.isEmpty(contentType)) {
            return REST_JSON_PATTERN.matcher(contentType).matches();
        }
        return false;
    }

}
