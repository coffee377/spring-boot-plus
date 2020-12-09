package com.voc.api.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/12 14:34
 */
public class ActionGrantedAuthority implements GrantedAuthority {

    private final ActionType action;

    private String prefix = "ACTION_";

    public ActionGrantedAuthority(ActionType action) {
        this.action = action;
    }

    @Override
    public String getAuthority() {
        return this.action.getText();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ActionGrantedAuthority) {
            return action.equals(((ActionGrantedAuthority) obj).action);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.action.getValue().hashCode();
    }

    @Override
    public String toString() {
        return this.action.getText();
    }

}
