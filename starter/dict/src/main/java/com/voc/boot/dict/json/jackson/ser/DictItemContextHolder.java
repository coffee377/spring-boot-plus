package com.voc.boot.dict.json.jackson.ser;

import com.voc.boot.dict.json.annotation.DictSerialize;
import com.voc.boot.dict.json.jackson.DictSerializeProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemContextHolder extends DictSerializeProperties {

    public DictItemContextHolder(DictSerializeProperties properties) {
        this.setScopes(properties.getScopes());
        this.setId(properties.getId());
        this.setValue(properties.getValue());
        this.setLabel(properties.getLabel());
        this.setDescription(properties.getDescription());
    }

    public DictItemContextHolder(DictSerializeProperties properties, DictSerialize... dictSerialize) {
        this(properties);
        this.combine(dictSerialize);
    }

    private void combine(DictSerialize... dictSerialize) {
        Arrays.stream(dictSerialize).filter(Objects::nonNull).forEach(dict -> {
            List<DictSerialize.Scope> scopes = Arrays.asList(dict.scopes());
            if (!ObjectUtils.isEmpty(scopes)) {
                this.setScopes(scopes);
            }
            if (StringUtils.hasText(dict.id())) {
                this.setId(dict.id());
            }
            if (StringUtils.hasText(dict.val())) {
                this.setValue(dict.val());
            }
            if (StringUtils.hasText(dict.text())) {
                this.setLabel(dict.text());
            }
            if (StringUtils.hasText(dict.description())) {
                this.setDescription(dict.description());
            }
        });
    }

    public boolean isWriteMultipleFields() {
        return !ObjectUtils.isEmpty(getScopes()) && getScopes().size() > 1;
    }

    public boolean canWriteId() {
        return StringUtils.hasText(getId()) && canWriteScope(DictSerialize.Scope.ID);
    }

    public boolean canWriteValue() {
        return StringUtils.hasText(getValue()) && canWriteScope(DictSerialize.Scope.VALUE);
    }

    public boolean canWriteLabel() {
        return StringUtils.hasText(getLabel()) && canWriteScope(DictSerialize.Scope.LABEL);
    }

    public boolean canWriteDescription() {
        return StringUtils.hasText(getDescription()) && canWriteScope(DictSerialize.Scope.DESCRIPTION);
    }

    public boolean canWriteCode() {
        return canWriteScope(DictSerialize.Scope.CODE);
    }

    private boolean canWriteScope(DictSerialize.Scope scope) {
        return !ObjectUtils.isEmpty(getScopes().stream().filter(s -> s.equals(scope)).findFirst().orElse(null));
    }
}
