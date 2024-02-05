package com.voc.common.api.authority;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/25 09:54
 */
public class AuthorityDescriptor implements IAuthorityDescriptor {
    private final String name;
    private final Integer position;
    private final String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getDescription() {
        return description;
    }

    AuthorityDescriptor(String name, Integer position, String description) {
        this.name = name;
        this.position = position;
        this.description = description;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Integer position;
        private String description;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder position(Integer mask) {
            this.position = mask;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public AuthorityDescriptor build() {
            return new AuthorityDescriptor(name, position, description);
        }
    }
}
