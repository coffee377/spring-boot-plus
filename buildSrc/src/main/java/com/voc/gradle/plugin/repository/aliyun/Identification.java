package com.voc.gradle.plugin.repository.aliyun;

import org.gradle.api.Named;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 19:36
 */

public class Identification implements Named, Serializable {
    private String name;
    private String id;
    private String type;
    private String hash;

    @Override
    public String getName() {
        return name;
    }

    public Identification(String name, String id, String type, String hash) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.hash = hash;
    }

    public Identification(RepositoryType type) {
        this(type.getType(), null, type.getType(), null);
    }

    public Identification(String name, String type) {
        this(name, null, type, null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", id, type, hash);
    }
}
