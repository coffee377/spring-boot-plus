package com.voc.gradle.plugin.config;

import com.voc.gradle.plugin.extension.DevToolExtension;
import com.voc.gradle.plugin.extension.PluginExtension;
import com.voc.gradle.plugin.util.StringUtils;
import org.gradle.api.Project;
import org.gradle.api.UnknownDomainObjectException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/07 17:29
 */
public interface IExtension<E extends PluginExtension> {
    /**
     * 开启 Extension 配置
     *
     * @return boolean
     */
    default boolean enableExtension() {
        return true;
    }

    /**
     * Convention 配置名称
     *
     * @return String
     */
    String getExtensionName();

    /**
     * 获取扩展配置
     *
     * @param project Project
     * @param type    Class<E>
     * @return E
     */
    default E getExtension(Project project, Class<DevToolExtension> type) {
        try {
            return (E) project.getExtensions().getByType(type);
        } catch (UnknownDomainObjectException ignored) {
        }
        return null;
    }

    /**
     * 添加扩展配置
     *
     * @param project Project
     * @param type    Class<E>
     */
    default void addExtension(Project project, Class<E> type) {
        String name = getExtensionName();
        E extension = null;
        if (type != null) {
            try {
                extension = project.getExtensions().getByType(type);
            } catch (UnknownDomainObjectException e) {
                if (enableExtension() && StringUtils.isNotEmpty(name)) {
                    project.getExtensions().create(name, type);
                }
            }
        }

    }
}
