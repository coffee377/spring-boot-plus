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

    public VocBanner() {
        this.resource = new ClassPathResource(DEFAULT_BANNER);
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        String banner = null;
        try {
            banner = StreamUtils.copyToString(this.resource.getInputStream(), environment.getProperty("spring.banner.charset", Charset.class, StandardCharsets.UTF_8));
        } catch (Exception ex) {
            List<String> bannerList = new ArrayList<>();
            bannerList.add("${AnsiColor.BRIGHT_RED}");
            bannerList.addAll(Arrays.asList(BANNER));
            bannerList.add("${AnsiColor.YELLOW}Application Name:${application.name}  Application Version:${application.formatted-version}");
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

    private Map<String, Object> getInfoMap(Environment environment) {
        String appVersion = environment.getProperty("spring.application.version");
        String appName = environment.getProperty("spring.application.name");
        String bootVersion = this.getBootVersion();
        Map<String, Object> versions = new HashMap<>(0);
        versions.put("application.name", appName);
        versions.put("application.version", appVersion);
        versions.put("spring-boot.version", this.getVersionString(bootVersion, false));
        versions.put("application.formatted-version", this.getVersionString(appVersion, true));
        versions.put("spring-boot.formatted-version", this.getVersionString(bootVersion, true));
        return versions;
    }

    private String getBootVersion() {
        return SpringBootVersion.getVersion();
    }

    private String getVersionString(String version, boolean format) {
        if (version == null) {
            return "";
        } else {
            return format ? String.format(" v %s ", version) : version;
        }
    }

}
