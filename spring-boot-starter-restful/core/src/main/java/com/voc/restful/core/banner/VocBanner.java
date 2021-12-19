package com.voc.restful.core.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiPropertySource;
import org.springframework.core.env.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/07/12 21:37
 */
public class VocBanner implements Banner {
    private static final String[] BANNER = new String[]{"********************************************************************************************************************************", "*                                                                                                                              *", "*    ___________.__             ____   ____    .__               ________   _____                _____  _____                  *", "*    \\__    ___/|  |__   ____   \\   \\ /   /___ |__| ____  ____   \\_____  \\_/ ____\\   ____  _____/ ____\\/ ____\\____   ____      *", "*      |    |   |  |  \\_/ __ \\   \\   Y   /  _ \\|  |/ ___\\/ __ \\   /   |   \\   __\\  _/ ___\\/  _ \\   __\\\\   __\\/ __ \\_/ __ \\     *", "*      |    |   |   Y  \\  ___/    \\     (  <_> )  \\  \\__\\  ___/  /    |    \\  |    \\  \\__(  <_> )  |   |  | \\  ___/\\  ___/     *", "*      |____|   |___|  /\\___  >    \\___/ \\____/|__|\\___  >___  > \\_______  /__|     \\___  >____/|__|   |__|  \\___  >\\___  >    *", "*                    \\/     \\/                         \\/    \\/          \\/             \\/                       \\/     \\/     *", "*                                                                                                                              *", "********************************************************************************************************************************"};
    private static final int STRAP_LINE_SIZE = 128;
    private static final String PADDING_CHAR = " ";
    private static final String DEFAULT_BANNER = "banner.txt";
    private final Resource resource;
    private boolean configuringAppVersion;
    private boolean configuringAppName;

    public VocBanner() {
        this.resource = new ClassPathResource(DEFAULT_BANNER);
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        String banner = null;
        this.ensureConfiguring(environment);

        try {
            banner = StreamUtils.copyToString(this.resource.getInputStream(), environment.getProperty("spring.banner.charset", Charset.class, StandardCharsets.UTF_8));
        } catch (Exception ex) {
            List<String> bannerList = new ArrayList<>();
            bannerList.add("${AnsiColor.BRIGHT_RED}");
            bannerList.addAll(Arrays.asList(BANNER));
            bannerList.add(getVersionInfo(configuringAppVersion, configuringAppName));
            banner = StringUtils.arrayToDelimitedString(bannerList.toArray(), "\n");
        } finally {
            PropertyResolver resolver;
            for (Iterator<PropertyResolver> iterator = this.getPropertyResolvers(environment, sourceClass).iterator(); iterator.hasNext(); banner = resolver.resolvePlaceholders(Objects.requireNonNull(banner))) {
                resolver = iterator.next();
            }
            printStream.println(banner);
            printStream.println();
        }
    }

    /**
     * 获取版本信息
     *
     * @param appVersion 是否配置了应用版本
     * @param appName    是否配置了应用名称
     * @return String
     */
    private String getVersionInfo(boolean appVersion, boolean appName) {
        StringBuilder info = new StringBuilder();
        info.append("${AnsiColor.YELLOW}");
        info.append("Spring Boot Version: ${spring-boot.formatted-version}\t");
        if (appVersion) {
            info.append("Application Version: ${application.formatted-version}\t");
        }
        if (appName) {
            info.append("Application Name: ${application.name}\t");
        }
        return info.toString();
    }

    private List<PropertyResolver> getPropertyResolvers(Environment environment, Class<?> sourceClass) {
        List<PropertyResolver> resolvers = new ArrayList<>();
        resolvers.add(environment);
        resolvers.add(this.getInfoResolver(environment));
        resolvers.add(this.getAnsiResolver());
        return resolvers;
    }

    private PropertyResolver getInfoResolver(Environment environment) {
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addLast(new MapPropertySource("info", this.getInfoMap(environment)));
        return new PropertySourcesPropertyResolver(propertySources);
    }

    private PropertyResolver getAnsiResolver() {
        MutablePropertySources sources = new MutablePropertySources();
        sources.addFirst(new AnsiPropertySource("ansi", true));
        return new PropertySourcesPropertyResolver(sources);
    }

    /**
     * 确保 APP 配置信息是否存在
     *
     * @param environment Environment
     */
    private void ensureConfiguring(Environment environment) {
        String appVersion = environment.getProperty("spring.application.version");
        String appName = environment.getProperty("spring.application.name");
        if (StringUtils.hasLength(appName)) {
            configuringAppName = true;
        }
        if (StringUtils.hasLength(appVersion)) {
            configuringAppVersion = true;
        }
    }

    private Map<String, Object> getInfoMap(Environment environment) {
        String bootVersion = this.getBootVersion();
        String appVersion = environment.getProperty("spring.application.version");
        String appName = environment.getProperty("spring.application.name");

        Map<String, Object> versions = new HashMap<>(0);
        versions.put("spring-boot.version", this.getAppVersion(bootVersion, false));
        versions.put("spring-boot.formatted-version", this.getAppVersion(bootVersion, true));

        if (configuringAppName) {
            versions.put("application.name", appName);
        }

        if (configuringAppVersion) {
            versions.put("application.version", appVersion);
            versions.put("application.formatted-version", this.getAppVersion(appVersion, true));
        }

        return versions;
    }

    /**
     * 获取 Spring Boot 版本
     *
     * @return 版本号
     */
    private String getBootVersion() {
        return SpringBootVersion.getVersion();
    }

    /**
     * 获取应用版本
     *
     * @param version 版本号
     * @param format  是否格式化
     * @return 版本号
     */
    private String getAppVersion(String version, boolean format) {
        if (StringUtils.hasLength(version)) {
            return format ? String.format("v%s ", version) : version;
        }
        return "";
    }

}
