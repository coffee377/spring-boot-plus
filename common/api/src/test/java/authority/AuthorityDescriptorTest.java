package authority;

import api.authority.AuthorityDescriptor;
import api.authority.IAuthorityDescriptor;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 19:22
 */
class AuthorityDescriptorTest {

    @Test
    void builder() {
        IAuthorityDescriptor authorityDescriptor = AuthorityDescriptor.builder().name("测试").mask(1).build();
        assertEquals("测试", authorityDescriptor.getName());
        assertEquals(1, authorityDescriptor.getMask());
        assertEquals(BigInteger.ONE, authorityDescriptor.get());
    }
}
