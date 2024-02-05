package com.voc.common.api.authority;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 10:23
 */
class AuthorityHelperTest {


    IAuthorityDescriptor def = AuthorityDescriptor.builder().name("默认").build(); // 0
    IAuthorityDescriptor add = AuthorityDescriptor.builder().name("添加").position(1).build(); // 1
    IAuthorityDescriptor delete = AuthorityDescriptor.builder().name("删除").position(2).build(); // 2
    IAuthorityDescriptor update = AuthorityDescriptor.builder().name("更新").position(3).build(); // 4
    IAuthorityDescriptor query = AuthorityDescriptor.builder().name("查询").position(4).build(); // 8
    IAuthorityDescriptor export = AuthorityDescriptor.builder().name("导出").position(5).build(); // 16

//    @Test
//    void print() {
//        log.info("{} {} => {}", def.getName(), def.getMask(), def.get().toString());
//        log.info("{} {} => {}", add.getName(), add.getMask(), add.get().toString());
//        log.info("{} {} => {}", delete.getName(), delete.getMask(), delete.get().toString());
//        log.info("{} {} => {}", update.getName(), update.getMask(), update.get().toString());
//        log.info("{} {} => {}", query.getName(), query.getMask(), query.get().toString());
//    }

    @Test
    void has() {
        BigInteger bigint = new BigInteger("6");
        assertTrue(AuthorityHelper.has(bigint, def));
        assertFalse(AuthorityHelper.has(bigint, add));
        assertTrue(AuthorityHelper.has(bigint, delete));
        assertTrue(AuthorityHelper.has(bigint, update));

        IAuthorities authorities = () -> bigint;
        assertTrue(AuthorityHelper.has(authorities, def));
        assertFalse(AuthorityHelper.has(authorities, add));
        assertTrue(AuthorityHelper.has(authorities, delete));
        assertTrue(AuthorityHelper.has(authorities, update));
    }

    @Test
    void hasAll() {
        BigInteger bigint = new BigInteger("14");
        assertTrue(AuthorityHelper.hasAll(bigint, delete));
        assertTrue(AuthorityHelper.hasAll(bigint, update));
        assertTrue(AuthorityHelper.hasAll(bigint, query));
        assertTrue(AuthorityHelper.hasAll(bigint, delete, update));
        assertTrue(AuthorityHelper.hasAll(bigint, delete, query));
        assertTrue(AuthorityHelper.hasAll(bigint, update, query));
        assertTrue(AuthorityHelper.hasAll(bigint, delete, update, query));
        assertFalse(AuthorityHelper.hasAll(bigint, add, delete, update, query));

        IAuthorities authorities = () -> bigint;
        assertTrue(AuthorityHelper.hasAll(authorities, delete));
        assertTrue(AuthorityHelper.hasAll(authorities, update));
        assertTrue(AuthorityHelper.hasAll(authorities, query));
        assertTrue(AuthorityHelper.hasAll(authorities, delete, update));
        assertTrue(AuthorityHelper.hasAll(authorities, delete, query));
        assertTrue(AuthorityHelper.hasAll(authorities, update, query));
        assertTrue(AuthorityHelper.hasAll(authorities, delete, update, query));
        assertFalse(AuthorityHelper.hasAll(authorities, add, delete, update, query));
    }

    @Test
    void hasAny() {
        BigInteger bigint = new BigInteger("14");
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, delete));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, update));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, query));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, delete, update));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, delete, query));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, update, query));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, delete, update, query));
        assertTrue(AuthorityHelper.hasAny(bigint, def, add, delete, update, query));

        IAuthorities authorities = () -> bigint;
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, delete));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, update));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, query));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, delete, update));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, delete, query));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, update, query));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, delete, update, query));
        assertTrue(AuthorityHelper.hasAny(authorities, def, add, delete, update, query));

        assertFalse(AuthorityHelper.hasAny(export.get(), add, delete, update, query));
    }

    @Test
    void hasNone() {
        BigInteger bigint = new BigInteger("14");
        assertFalse(AuthorityHelper.hasNone(bigint, def, add, delete, export));
        assertFalse(AuthorityHelper.hasNone(bigint, def, export));
        assertTrue(AuthorityHelper.hasNone(bigint, export));

        IAuthorities authorities = () -> bigint;
        assertFalse(AuthorityHelper.hasNone(authorities, def, add, delete, export));
        assertFalse(AuthorityHelper.hasNone(authorities, def, export));
        assertTrue(AuthorityHelper.hasNone(authorities, export));
    }
}
