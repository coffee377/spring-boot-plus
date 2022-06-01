package com.voc.common.util;

import com.voc.common.authority.IAuthorities;
import com.voc.common.authority.IAuthorityDescriptor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * 权限工具类
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/25 01:04
 */
public class AuthorityUtils {

    /**
     * 是否拥有某项权限
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定权限
     */
    public static boolean has(BigInteger authorities, IAuthorityDescriptor descriptor) {
        return authorities != null && descriptor.get().or(authorities).equals(authorities);
    }

    /**
     * 是否拥有某项权限
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定权限
     */
    public static boolean has(IAuthorities authorities, IAuthorityDescriptor descriptor) {
        return has(authorities.get(), descriptor);
    }

    /**
     * 是否拥有待检查权限的所有项
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定所有权限
     */
    public static boolean hasAll(BigInteger authorities, IAuthorityDescriptor descriptor, IAuthorityDescriptor... others) {
        boolean b1 = has(authorities, descriptor);
        boolean b2 = Arrays.stream(others).allMatch(desc -> has(authorities, desc));
        return b1 && b2;
    }

    /**
     * 是否拥有待检查权限的所有项
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定所有权限
     */
    public static boolean hasAll(IAuthorities authorities, IAuthorityDescriptor descriptor, IAuthorityDescriptor... others) {
        return hasAll(authorities.get(), descriptor, others);
    }

    /**
     * 是否拥有待检查权限的某几项
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定所有权限
     */
    public static boolean hasAny(BigInteger authorities, IAuthorityDescriptor descriptor,
                                 IAuthorityDescriptor... others) {
        boolean b1 = has(authorities, descriptor);
        boolean b2 = Arrays.stream(others).anyMatch(desc -> has(authorities, desc));
        return b1 || b2;
    }

    /**
     * 是否拥有待检查权限的某几项
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否包含指定所有权限
     */
    public static boolean hasAny(IAuthorities authorities, IAuthorityDescriptor descriptor,
                                 IAuthorityDescriptor... others) {
        return hasAny(authorities.get(), descriptor, others);
    }

    /**
     * 没有待检查权限的任何一项
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否不包含指定的所有权限
     */
    public static boolean hasNone(BigInteger authorities, IAuthorityDescriptor descriptor,
                                  IAuthorityDescriptor... others) {
        boolean b1 = !has(authorities, descriptor);
        boolean b2 = Arrays.stream(others).noneMatch(desc -> has(authorities, desc));
        return b1 && b2;
    }

    /**
     * 没有待检查权限的任何一项
     *
     * @param authorities 权限集合
     * @param descriptor  权限描述
     * @return 是否不包含指定的所有权限
     */
    public static boolean hasNone(IAuthorities authorities, IAuthorityDescriptor descriptor,
                                  IAuthorityDescriptor... others) {
        return hasNone(authorities.get(), descriptor, others);
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
        return source;
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
