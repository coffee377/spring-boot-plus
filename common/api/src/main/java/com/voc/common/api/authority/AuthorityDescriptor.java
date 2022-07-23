package com.voc.common.api.authority;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/25 09:54
 */
public class AuthorityDescriptor implements IAuthorityDescriptor {

    private final String name;
    private final Integer mask;

    AuthorityDescriptor(String name, Integer mask) {
        this.name = name;
        this.mask = mask;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getMask() {
        return mask;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Integer mask;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder mask(Integer mask) {
            this.mask = mask;
            return this;
        }

        public AuthorityDescriptor build() {
            return new AuthorityDescriptor(name, mask);
        }
    }
}
