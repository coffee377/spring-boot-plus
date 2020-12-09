package com.voc.api.utils;

import com.voc.api.dict.EnumDict;
import com.voc.api.dict.IMasks;
import com.voc.api.enums.ActionGrantedAuthority;
import com.voc.api.enums.ActionType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/12 15:21
 */
public class ActionAuthorityUtil {

    /**
     * Creates a array of GrantedAuthority objects from a comma-separated string
     * representation (e.g. "ACTION_A, ACTION_B, ACTION_C").
     *
     * @param authorityString the comma-separated string
     * @return the authorities created by tokenizing the string
     */
    public static List<GrantedAuthority> commaSeparatedStringToAuthorityList(String authorityString) {
        return createAuthorityList(StringUtils.tokenizeToStringArray(authorityString, ","));
    }

    /**
     * Converts authorities into a List of GrantedAuthority objects.
     *
     * @param authorities the authorities to convert
     * @return a List of GrantedAuthority objects
     */
    public static List<GrantedAuthority> createAuthorityList(String... authorities) {
        return Arrays.stream(authorities)
                .map(authority -> EnumDict.find(ActionType.class, authority))
                .filter(Optional::isPresent)
                .map(item -> new ActionGrantedAuthority(item.get()))
                .collect(Collectors.toList());
    }

    /**
     * 从功能获取权限
     *
     * @param actions IActions
     * @return List<GrantedAuthority>
     */
    public static List<GrantedAuthority> create4Functions(IMasks actions) {
        return EnumDict.contains(ActionType.class, actions).stream()
                .map(ActionGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * 是否拥有某项操作权限
     *
     * @param masks  IMasks
     * @param action ActionType
     * @return boolean
     */
    public static boolean hasAction(IMasks masks, ActionType action) {
        BigInteger sumMask = masks.get();
        return sumMask != null && action.getMask().or(sumMask).equals(sumMask);
    }

    /**
     * 添加额外权限
     *
     * @param masks      IMasks
     * @param additional ActionType[]
     * @return List<GrantedAuthority>
     */
    public List<GrantedAuthority> add(IMasks masks, ActionType... additional) {
        List<ActionType> result = EnumDict.contains(ActionType.class, masks);
        Arrays.stream(additional).filter(actionType -> !result.contains(actionType)).forEach(result::add);
        return result.stream().map(ActionGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * 移除额外权限
     *
     * @param masks      IMasks
     * @param additional ActionType[]
     * @return List<GrantedAuthority>
     */
    public List<GrantedAuthority> remove(IMasks masks, ActionType... additional) {
        List<ActionType> result = EnumDict.contains(ActionType.class, masks);
        Arrays.stream(additional).filter(result::contains).forEach(result::remove);
        return result.stream().map(ActionGrantedAuthority::new).collect(Collectors.toList());
    }

}
