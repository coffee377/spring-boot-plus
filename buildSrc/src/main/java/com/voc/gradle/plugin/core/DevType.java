package com.voc.gradle.plugin.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.gradle.api.publish.maven.MavenPublication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/30 09:04
 */
@Getter
@AllArgsConstructor
public enum DevType {
    BOM("pom", "Pom"),
    LIB("jar", "Lib"),
    APP("jar", "App"),
    WAR("war", "War"),
    ;

    /**
     * @see org.gradle.api.publish.maven.MavenPom#setPackaging(String)
     */
    private final String packaging;

    /**
     * @see MavenPublication#getName()
     */
    private final String pubName;

    public static boolean isJar(DevType devType) {
        return "jar".equals(devType.getPackaging());
    }

    public static boolean isPom(DevType devType) {
        return "pom".equals(devType.getPackaging());
    }

    public static boolean isWar(DevType devType) {
        return "war".equals(devType.getPackaging());
    }

    public static DevType of(String name) {
        Optional<DevType> first = Arrays.stream(DevType.values())
                .filter(devType -> devType.name().equalsIgnoreCase(name)).findFirst();

        DevType devType = first.orElseThrow(() -> {
            List<String> collect = Arrays.stream(DevType.values()).map(Enum::name).collect(Collectors.toList());
            return new RuntimeException(String.format("name must be in %s", collect));
        });
        return devType;
    }

}
