package com.voc.gradle.plugin;

import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Plugin;
import org.gradle.api.invocation.Gradle;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/17 09:31
 */
//@Slf4j
public class GradleInitPlugin implements Plugin<Gradle> {
    @Override
    public void apply(Gradle target) {
        System.out.println(target.getGradleVersion());
//        target.
//        log.info("{} => ", target.getGradleVersion());
    }
}
