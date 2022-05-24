package com.voc.common.util;

import com.voc.common.authority.IAuthorities;
import com.voc.common.authority.IAuthorityDescriptor;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/25 01:04
 */
public class AuthorityUtils {

    /**
     * 是否拥有某项操作权限
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定权限
     */
    public static boolean hasAuthority(IAuthorities authorities, IAuthorityDescriptor descriptor) {
        BigInteger sumMask = authorities.get();
        return sumMask != null && descriptor.get().or(sumMask).equals(sumMask);
    }

    /**
     * 添加权限
     *
     * @param source     原始权限
     * @param descriptor 需要添加的权限
     * @param others     其他需要添加的权限
     * @return IAuthorities 添加后新的权限集合
     */
    public static IAuthorities add(IAuthorities source, IAuthorityDescriptor descriptor, IAuthorityDescriptor... others) {
        return null;
    }

    public static IAuthorities add(IAuthorities source, IAuthorityDescriptor descriptor, List<IAuthorityDescriptor> others) {
        return null;
    }

    /**
     * 添加权限
     *
     * @param source     原始权限
     * @param descriptor 需要移除的权限
     * @param others     其他需要移除的权限
     * @return IAuthorities 移除后新的权限集合
     */
    public static IAuthorities remove(IAuthorities source, IAuthorityDescriptor descriptor, IAuthorityDescriptor... others) {
        return null;
    }

    public static IAuthorities remove(IAuthorities source, IAuthorityDescriptor descriptor, List<IAuthorityDescriptor>... others) {
        return null;
    }
}
