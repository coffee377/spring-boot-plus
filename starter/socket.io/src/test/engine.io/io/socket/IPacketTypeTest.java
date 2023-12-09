package io.socket;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class IPacketTypeTest {

    static class TestPacketType implements IPacketType {

        private final int value;

        public TestPacketType(int value) {
            this.value = value;
        }

        @Override
        public int getTypeValue() {
            return value;
        }
    }


    @Test
    public void packType() {
        TestPacketType type = new TestPacketType(0);
        log.debug("int => {}", type.getTypeValue());
        log.debug("byte => {}", type.getTypeByte());
        log.debug("char => {}", type.getTypeChar());
        log.debug("string  => {}", type.getTypeSting());
    }

}
