package com.voc.gradle.plugin.embedded;

import com.voc.gradle.plugin.util.StringUtils;
import lombok.Getter;
import org.gradle.api.artifacts.ExcludeRule;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/07/24 18:54
 */
@Getter
public enum Dependency {
    /**
     *
     */
    JUNIT("junit", "junit", "4.12"),
    JUNIT_5("org.junit.jupiter", "junit-jupiter-engine", "5.5.2"),
    SLF4J_API("org.slf4j", "slf4j-api", "1.7.25"),

    JCrack("com.voc", "JCrack", "2.0.2-RELEASE"),

    LOMBOK("org.projectlombok", "lombok", "1.18.4"),
    LOGBACK("ch.qos.logback", "logback-classic", "1.2.3"),
    FAST_JSON("com.alibaba", "fastjson", "1.2.58"),
    GOOGLE_AUTO_SERVICE("com.google.auto.service", "auto-service", "1.0-rc4"),

    KOTLIN_GRADLE_PLUGIN("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.3.40"),
    KOTLIN_JDK("org.jetbrains.kotlin", "kotlin-stdlib", "1.3.40"),
    KOTLIN_JDK_7("org.jetbrains.kotlin", "kotlin-stdlib-jdk7", "1.3.40"),
    KOTLIN_JDK_8("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", "1.3.40"),
    JAVAX_SERVLET_API("javax.servlet", "javax.servlet-api", "3.1.0"),

    /* FineReport */
    FINE_ACTIVATOR("com.fr.activator", "fine-activator", "10.0.2019.07.03"),
    FINE_CORE("com.fr.core", "fine-core", "10.0.2019.07.03"),
    FINE_DATASOURCE("com.fr.datasource", "fine-datasource", "10.0.2019.07.03"),
    FINE_DECISION("com.fr.decision", "fine-decision", "10.0.2019.07.03"),
    FINE_DECISION_REPORT("com.fr.decision", "fine-decision-report", "10.0.2019.07.03"),
    FINE_ACCUMULATOR("com.fr.intelligence", "fine-accumulator", "10.0.2019.07.03"),
    FINE_SWIFT("com.fr.intelligence", "fine-swift", "10.0.2019.07.03"),
    FINE_REPORT_ENGINE("com.fr.report", "fine-report-engine", "10.0.2019.07.03"),
    FINE_SCHEDULE("com.fr.schedule", "fine-schedule", ""),
    FINE_SCHEDULE_REPORT("com.fr.schedule", "fine-schedule-report", "10.0.2019.07.03"),
    FINE_THIRD("com.fr.third", "fine-third", "10.0.2019.07.03"),
    FINE_WEBUI("com.fr.webui", "fine-webui", "10.0.2019.07.03"),
    VISUALVM("com.fr.third", "visualvm", "2.3"),

    /* Fine Designer */
    FINE_REPORT_DESIGNER("com.fr.report", "fine-report-designer", "10.0.2019.07.03"),
    SOCKET_CLIENT("io.socket", "socket.io-client", "0.7.0"),
    ASPECTJ("org.aspectj", "aspectjrt", "1.6.9"),
    SWEXPL("org.swingexplorer", "swexpl", "2.0.1"),
    SWAG("org.swingexplorer", "swag", "1.0"),
    TOMCAT_CATALINA("org.apache.tomcat", "tomcat-catalina", "8.5.42"),
    TOMCAT_JASPER("org.apache.tomcat", "tomcat-jasper", "8.5.42"),

    FLUENT_HC("org.apache.httpcomponents", "fluent-hc", "4.3.1"),
    ZOOKEEPER("org.apache.zookeeper", "zookeeper", "3.4.6"),
    NETTY_ALL("io.netty", "netty-all", "4.1.17"),
    ;

    private String group;
    private String name;
    private String version;
    private boolean transitive;
    private String classifier;
    private ExcludeRule[] excludeRule;
    private ExtraProps extraProps;

    public Dependency setGroup(String group) {
        if (StringUtils.isNotEmpty(group) && !group.equals(this.group)) {
            this.group = group;
        }
        return this;
    }

    public Dependency setName(String name) {
        if (StringUtils.isNotEmpty(name) && !name.equals(this.name)) {
            this.name = name;
        }
        return this;
    }

    public Dependency setVersion(String version) {
        if (StringUtils.isNotEmpty(version) && !version.equals(this.version)) {
            this.version = version;
        }
        return this;
    }

    public Dependency setTransitive(boolean transitive) {
        if (this.transitive != transitive) {
            this.transitive = transitive;
        }
        return this;
    }

    public Dependency setClassifier(String classifier) {
        if (StringUtils.isNotEmpty(classifier) && !classifier.equals(this.classifier)) {
            this.classifier = classifier;
        }
        return this;
    }

    public Dependency setExcludeRule(ExcludeRule[] excludeRule) {
        if (excludeRule != null && excludeRule.length > 0) {
            this.excludeRule = excludeRule;
        }
        return this;
    }

    Dependency(String group, String name, String version) {
        this.group = group;
        this.name = name;
        this.version = version;
        this.transitive = true;
    }

    Dependency(String group, String name, ExtraProps version) {
        this.group = group;
        this.name = name;
//        this.version = version.;
        this.transitive = true;
    }


}
