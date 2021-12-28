package com.voc.gradle.plugin.util;

import com.voc.gradle.plugin.repository.RepositoryInfo;
import com.voc.gradle.plugin.repository.aliyun.AliYunRepositoryInfo;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 14:59
 */
public class RepositoryUtil {

    /**
     * 添加仓库
     *
     * @param repositoryHandler RepositoryHandler
     * @param repositoryInfo    RepositoryInfo
     * @deprecated
     */
    public static void add(RepositoryHandler repositoryHandler, RepositoryInfo repositoryInfo) {
        RepositoryUtil.addRepository(repositoryHandler, repositoryInfo);
    }

    /**
     * 添加仓库
     *
     * @param repositoryHandler RepositoryHandler
     * @param repositoryInfo    RepositoryInfo
     */
    public static void addRepository(@NonNull RepositoryHandler repositoryHandler, @Nullable RepositoryInfo repositoryInfo) {
        RepositoryUtil.addRepository(repositoryHandler, repositoryInfo, null);
    }

    /**
     * 添加仓库
     *
     * @param repositoryHandler RepositoryHandler
     * @param repositoryInfo    RepositoryInfo
     * @param url               String
     */
    private static void addRepository(@NonNull RepositoryHandler repositoryHandler, @Nullable RepositoryInfo repositoryInfo,
                                      @Nullable String url) {
        Optional.ofNullable(repositoryInfo).ifPresent(info -> {
            String realUrl = StringUtils.isNotEmpty(url) ? url : info.getUrl();
            if (StringUtils.isNotEmpty(realUrl)) {
                MavenArtifactRepository maven = repositoryHandler.maven(mavenArtifactRepository -> {
                    Optional.of(info.getName()).ifPresent(mavenArtifactRepository::setName);
                    mavenArtifactRepository.setUrl(realUrl);
                    mavenArtifactRepository.credentials(credentials -> {
                        Optional.ofNullable(info.getUsername()).ifPresent(credentials::setUsername);
                        Optional.ofNullable(info.getPassword()).ifPresent(credentials::setPassword);
                    });
                });
                repositoryHandler.add(maven);
            }
        });

    }

    /**
     * 添加阿里云效仓库
     *
     * @param repositoryHandler    RepositoryHandler
     * @param aliYunRepositoryInfo AliYunRepositoryInfo
     */
    public static void addAliYunRepository(@NonNull RepositoryHandler repositoryHandler,
                                           @Nullable AliYunRepositoryInfo aliYunRepositoryInfo) {
        Optional.ofNullable(aliYunRepositoryInfo).ifPresent(info -> {
            List<String> urls = Arrays.asList(info.getReleaseUrl(), info.getSnapshotUrl());
            urls.forEach(url -> RepositoryUtil.addRepository(repositoryHandler, info, url));
        });

    }
}
