package com.voc.api.response;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 10:13
 */
//@Mapper
public interface ResultMapper {
//    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    /**
     * 对象转换
     *
     * @param resultBuilder Result.Builder
     * @return Result
     */
//    @Mappings({
//            @Mapping(source = "success", target = "success"),
//            @Mapping(source = "code", target = "code"),
//            @Mapping(source = "message", target = "message"),
//            @Mapping(source = "data", target = "data")
//    })
    Result resultBuilderToResult(Result.Builder resultBuilder);

}
