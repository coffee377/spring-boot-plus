package com.voc.dingtalk.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.taobao.api.ApiException;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 20:22
 */
@Slf4j
@RestController
@RequestMapping("/dingtalk")
public class DingTalkController {

    @GetMapping("/test")
    public String ddd() {
        return "测试";
    }

    @GetMapping("/test2")
    public Result<Object> ddd2() {
        return Result.builder().success("成功", "测试2").build();
    }

    @GetMapping("/test3")
    public Integer ddd3() {
        return 3;
    }

    @GetMapping("/test4")
    public Demo ddd4() {
        Demo demo = new Demo();
        demo.setBirthday4(new Date());
        demo.setAge(30);
        demo.setName("zs");
        demo.setB1(true);
        return demo;
    }

    @PostMapping("/")
    public Map<String, Object> scanLogin() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        return new HashMap<>(linkedHashMap);
    }

    public static void main(String[] args) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/create");
            OapiProcessinstanceCreateRequest req = new OapiProcessinstanceCreateRequest();
            req.setProcessCode("PROC-EF6YZNVRN2-1YKLU2MFUATK10NO07TS3-DP8VXP3J-31"); // 审批流的唯一码
            req.setOriginatorUserId("02140408367343"); // 审批实例发起人的userid
            req.setDeptId(31871420L); // 发起人所在的部门

            req.setApprovers("02140408367343"); // 审批人 张银林
            req.setCcList("02140408367343"); // 抄送人 吴春梅
            req.setCcPosition("FINISH");
            List<OapiProcessinstanceCreateRequest.FormComponentValueVo> formComponentValueVoList = new ArrayList<>();

            OapiProcessinstanceCreateRequest.FormComponentValueVo formComponentValueVo1 =
                    new OapiProcessinstanceCreateRequest.FormComponentValueVo();
            formComponentValueVo1.setName("加班人");

            formComponentValueVo1.setValue("[\"02140408367343\"]");
//            formComponentValueVo1.setValue("[\"021404083621658683\"]");
            formComponentValueVoList.add(formComponentValueVo1);

            OapiProcessinstanceCreateRequest.FormComponentValueVo formComponentValueVo2 =
                    new OapiProcessinstanceCreateRequest.FormComponentValueVo();
            formComponentValueVo2.setName("开始时间");
            formComponentValueVo2.setValue("2021-05-18");
            formComponentValueVoList.add(formComponentValueVo2);


            OapiProcessinstanceCreateRequest.FormComponentValueVo formComponentValueVo3 =
                    new OapiProcessinstanceCreateRequest.FormComponentValueVo();
            formComponentValueVo3.setName("结束时间");
            formComponentValueVo3.setValue("2021-05-19");
            formComponentValueVoList.add(formComponentValueVo3);

            OapiProcessinstanceCreateRequest.FormComponentValueVo formComponentValueVo4 =
                    new OapiProcessinstanceCreateRequest.FormComponentValueVo();
            formComponentValueVo4.setName("时长");
            formComponentValueVo4.setValue("4");
            formComponentValueVoList.add(formComponentValueVo4);

            OapiProcessinstanceCreateRequest.FormComponentValueVo formComponentValueVo5 =
                    new OapiProcessinstanceCreateRequest.FormComponentValueVo();
            formComponentValueVo5.setName("加班原因");
            formComponentValueVo5.setValue("黄山休宁返贫监测项目");
            formComponentValueVoList.add(formComponentValueVo4);


//            OapiProcessinstanceCreateRequest.FormComponentValueVo formComponentValueVo6 =
//                    new OapiProcessinstanceCreateRequest.FormComponentValueVo();
//            formComponentValueVo6.setName("加班类型");
//            formComponentValueVo6.setValue("项目支撑");
//            formComponentValueVoList.add(formComponentValueVo4);

//            formComponentValueVo.setExtValue("总天数:1");
            req.setFormComponentValues(formComponentValueVoList);
            OapiProcessinstanceCreateResponse rsp = client.execute(req, "a592016877f133d49edebc56e3fb06b3");
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
