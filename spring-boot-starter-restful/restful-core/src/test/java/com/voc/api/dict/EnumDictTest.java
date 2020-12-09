package com.voc.api.dict;

import com.voc.api.enums.ActionType;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/12 18:12
 */
class EnumDictTest {

    @Test
    void findAll() {
        List<ActionType> all = EnumDict.findAll(ActionType.class);
        assertIterableEquals(all, Arrays.asList(ActionType.values().clone()));
    }

    @Test
    void findByCondition() {
        List<ActionType> actionTypes = EnumDict.findByCondition(ActionType.class,
                item -> item.getText().equalsIgnoreCase("add") || item.getText().equalsIgnoreCase("lock"));
        assertEquals(2, actionTypes.size());
        assertTrue(actionTypes.contains(ActionType.ADD));
        assertTrue(actionTypes.contains(ActionType.LOCK));
    }

    @Test
    void contains() {
        IMasks masks = DefaultActions.ALL;
        List<ActionType> contains = EnumDict.contains(ActionType.class, masks);
        assertEquals(contains.size(), ActionType.values().length);
    }

    @Test
    void findByValue() {
        Optional<ActionType> value1 = EnumDict.findByValue(ActionType.class, ActionType.ENABLE.getValue());
        Optional<ActionType> value2 = EnumDict.findByValue(ActionType.class, "0");
        assertTrue(value1.isPresent());
        assertFalse(value2.isPresent());
    }

    @Test
    void findByText() {
        Optional<ActionType> actionType = EnumDict.findByText(ActionType.class, "enable");
        assertTrue(actionType.isPresent());
        assertEquals(ActionType.ENABLE, actionType.get());
    }

    @Test
    void find() {
        Optional<ActionType> actionType = EnumDict.find(ActionType.class, BigInteger.ONE.shiftLeft(5));
        assertTrue(actionType.isPresent());
        Optional<ActionType> first = Arrays.stream(ActionType.values()).filter(item -> item.ordinal() == 5).findFirst();
        assertTrue(first.isPresent());
        assertEquals(first.get(), actionType.get());
    }
}
