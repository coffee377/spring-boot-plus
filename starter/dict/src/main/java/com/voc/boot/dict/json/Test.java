package com.voc.boot.dict.json;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Test {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        boolean b1 = Objects.hashCode(list) == 0;
        System.out.println(b1);

        String s1 = "";
        boolean b = Objects.hashCode(s1) == 0;
        System.out.println(b);

        Integer num1=200;
        Integer num2=200;
        if (num1 != num2) {
            System.out.print(1);
        }else {
            System.out.print(2);
        }
        if (!num1.equals(num2)) {
            System.out.print(3);
        }else {
            System.out.print(4);
        }
    }
}
