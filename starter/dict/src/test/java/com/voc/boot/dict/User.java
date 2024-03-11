package com.voc.boot.dict;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voc.boot.dict.json.annotation.DictSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String id;

    private String name;

    private Sex sex;

    @DictSerialize(DictSerialize.Scope.LABEL)
    private Sex sex1;

    @DictSerialize(DictSerialize.Scope.ID)
    private Sex sex2;

    @DictSerialize(DictSerialize.Scope.DESCRIPTION)
    private Sex sex3;

    @DictSerialize({DictSerialize.Scope.ID, DictSerialize.Scope.VALUE, DictSerialize.Scope.LABEL, DictSerialize.Scope.DESCRIPTION})
    private Sex sex4;
}
