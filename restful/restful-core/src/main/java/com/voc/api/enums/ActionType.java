//package com.voc.api.enums;
//
//import com.voc.api.dict.EnumDict;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2020/10/12 09:26
// */
//public enum ActionType implements EnumDict {
//    /**
//     * 页面级操作权限
//     */
//    ADD("ADD", "新增"),
//    DELETE("DELETE", "删除"),
//    UPDATE("UPDATE", "修改"),
//    QUERY("QUERY", "查询"),
//    DETAIL("DETAIL", "明细"),
//    IMPORT("IMPORT", "导入"),
//    EXPORT("EXPORT", "导出"),
//    DISABLE("DISABLE", "禁用"),
//    ENABLE("ENABLE", "启用"),
//    LOCK("LOCK", "锁定");
//
//    private final String text;
//
//    private final String comments;
//
//    ActionType(String text, String comments) {
//        this.text = text;
//        this.comments = comments;
//    }
//
//    @Override
//    public String getText() {
//        return text;
//    }
//
//    @Override
//    public String getComments() {
//        return comments;
//    }
//
//}
