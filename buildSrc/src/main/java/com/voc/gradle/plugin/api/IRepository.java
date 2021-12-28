package com.voc.gradle.plugin.api;

import com.voc.gradle.plugin.repository.MavenRepository;
import com.voc.gradle.plugin.repository.RepositoryInfo;
import com.voc.gradle.plugin.repository.aliyun.AliYunRepositoryInfo;
import com.voc.gradle.plugin.util.RepositoryUtil;
import com.voc.gradle.plugin.util.StringUtils;
import org.gradle.api.Action;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.Nullable;

import java.io.File;
import java.net.URI;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/15 10:04
 */
public interface IRepository extends IProject {

    /**
     * 添加 maven 仓库
     *
     * @param repositoryUrl 仓库地址
     * @param username      用户名
     * @param password      密码
     */
    default void addMavenRepository(@NonNull String repositoryUrl, @Nullable String username, @Nullable String password) {
        addMavenRepository(repositoryInfo -> {
            repositoryInfo.setUrl(repositoryUrl);
            repositoryInfo.setUsername(username);
            repositoryInfo.setPassword(password);
        });
    }

    /**
     * 添加 maven 仓库
     *
     * @param repositoryInfo 仓库xin
     */
    default void addMavenRepository(RepositoryInfo repositoryInfo) {
        RepositoryHandler repositoryHandler = getProject().getRepositories();
        RepositoryUtil.addRepository(repositoryHandler, repositoryInfo);
    }

    /**
     * 添加 maven 仓库
     *
     * @param repositoryInfoAction Action<RepositoryInfo>
     */
    default void addMavenRepository(Action<RepositoryInfo> repositoryInfoAction) {
        RepositoryInfo repositoryInfo = new MavenRepository();
        repositoryInfoAction.execute(repositoryInfo);
        addMavenRepository(repositoryInfo);
    }

    /**
     * 添加阿里云效仓库
     *
     * @param aliYunRepositoryInfo AliYunRepositoryInfo
     */
    default void addAliYunMavenRepository(AliYunRepositoryInfo aliYunRepositoryInfo) {
        RepositoryHandler repositoryHandler = getProject().getRepositories();
        RepositoryUtil.addAliYunRepository(repositoryHandler, aliYunRepositoryInfo);
    }

    /**
     * 添加无需认证的 maven 仓库
     *
     * @param url 仓库地址
     */
    default void addMavenRepository(String url) {
        addMavenRepository(url, null, null);
    }

    /**
     * 添加本地仓库地址
     *
     * @param localMavenRepository 本地路径
     */
    default void addMavenLocal(String localMavenRepository) {
        RepositoryHandler repositoryHandler = getProject().getRepositories();
        MavenArtifactRepository mavenLocal = repositoryHandler.mavenLocal();
        if (StringUtils.isNotEmpty(localMavenRepository)) {
            File file = new File(localMavenRepository);
            URI uri = file.toURI();
            if (file.exists() && !mavenLocal.getUrl().equals(uri)) {
                mavenLocal.setUrl(uri);
            }
        }
        repositoryHandler.add(mavenLocal);
    }

    /**
     * 添加中央仓库地址
     */
    default void addMavenCentral() {
        RepositoryHandler repositoryHandler = getProject().getRepositories();
        repositoryHandler.add(repositoryHandler.mavenCentral());
    }

}
