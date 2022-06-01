package com.voc.common.util;

import com.voc.common.authority.AuthorityDescriptor;
import com.voc.common.authority.IAuthorities;
import com.voc.common.authority.IAuthorityDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/25 09:51
 */
@Slf4j
class AuthorityUtilsTest {

    IAuthorityDescriptor def = AuthorityDescriptor.builder().name("默认").build(); // 0
    IAuthorityDescriptor add = AuthorityDescriptor.builder().name("添加").mask(1).build(); // 1
    IAuthorityDescriptor delete = AuthorityDescriptor.builder().name("删除").mask(2).build(); // 2
    IAuthorityDescriptor update = AuthorityDescriptor.builder().name("更新").mask(3).build(); // 4
    IAuthorityDescriptor query = AuthorityDescriptor.builder().name("查询").mask(4).build(); // 8
    IAuthorityDescriptor export = AuthorityDescriptor.builder().name("导出").mask(5).build(); // 16

    @Test
    void print() {
        log.info("{} {} => {}", def.getName(), def.getMask(), def.get().toString());
        log.info("{} {} => {}", add.getName(), add.getMask(), add.get().toString());
        log.info("{} {} => {}", delete.getName(), delete.getMask(), delete.get().toString());
        log.info("{} {} => {}", update.getName(), update.getMask(), update.get().toString());
        log.info("{} {} => {}", query.getName(), query.getMask(), query.get().toString());
    }

    @Test
    void has() {
        BigInteger bigint = new BigInteger("6");
        assertTrue(AuthorityUtils.has(bigint, def));
        assertFalse(AuthorityUtils.has(bigint, add));
        assertTrue(AuthorityUtils.has(bigint, delete));
        assertTrue(AuthorityUtils.has(bigint, update));

        IAuthorities authorities = () -> bigint;
        assertTrue(AuthorityUtils.has(authorities, def));
        assertFalse(AuthorityUtils.has(authorities, add));
        assertTrue(AuthorityUtils.has(authorities, delete));
        assertTrue(AuthorityUtils.has(authorities, update));
    }

    @Test
    void hasAll() {
        BigInteger bigint = new BigInteger("14");
        assertTrue(AuthorityUtils.hasAll(bigint, delete));
        assertTrue(AuthorityUtils.hasAll(bigint, update));
        assertTrue(AuthorityUtils.hasAll(bigint, query));
        assertTrue(AuthorityUtils.hasAll(bigint, delete, update));
        assertTrue(AuthorityUtils.hasAll(bigint, delete, query));
        assertTrue(AuthorityUtils.hasAll(bigint, update, query));
        assertTrue(AuthorityUtils.hasAll(bigint, delete, update, query));
        assertFalse(AuthorityUtils.hasAll(bigint, add, delete, update, query));

        IAuthorities authorities = () -> bigint;
        assertTrue(AuthorityUtils.hasAll(authorities, delete));
        assertTrue(AuthorityUtils.hasAll(authorities, update));
        assertTrue(AuthorityUtils.hasAll(authorities, query));
        assertTrue(AuthorityUtils.hasAll(authorities, delete, update));
        assertTrue(AuthorityUtils.hasAll(authorities, delete, query));
        assertTrue(AuthorityUtils.hasAll(authorities, update, query));
        assertTrue(AuthorityUtils.hasAll(authorities, delete, update, query));
        assertFalse(AuthorityUtils.hasAll(authorities, add, delete, update, query));
    }

    @Test
    void hasAny() {
        BigInteger bigint = new BigInteger("14");
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, delete));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, update));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, query));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, delete, update));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, delete, query));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, update, query));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, delete, update, query));
        assertTrue(AuthorityUtils.hasAny(bigint, def, add, delete, update, query));

        IAuthorities authorities = () -> bigint;
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, delete));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, update));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, query));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, delete, update));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, delete, query));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, update, query));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, delete, update, query));
        assertTrue(AuthorityUtils.hasAny(authorities, def, add, delete, update, query));

        assertFalse(AuthorityUtils.hasAny(export.get(), add, delete, update, query));
    }

    @Test
    void hasNone() {
        BigInteger bigint = new BigInteger("14");
        assertFalse(AuthorityUtils.hasNone(bigint, def, add, delete, export));
        assertFalse(AuthorityUtils.hasNone(bigint, def, export));
        assertTrue(AuthorityUtils.hasNone(bigint, export));

        IAuthorities authorities = () -> bigint;
        assertFalse(AuthorityUtils.hasNone(authorities, def, add, delete, export));
        assertFalse(AuthorityUtils.hasNone(authorities, def, export));
        assertTrue(AuthorityUtils.hasNone(authorities, export));
    }
}
