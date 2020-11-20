//package com.voc.demo.controller;
//
//import com.voc.api.response.BizException;
//import com.voc.api.response.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2020/09/25 17:21
// */
//@Slf4j
//@RestController
//public class MessagesController {
//
//    @Resource
//    private ClientRegistrationRepository clientRegistrationRepository;
//
//    @GetMapping(path = "/")
//    public String index() {
//        throw new BizException("测试异常");
//    }
//
//    @GetMapping("/messages")
//    public Result getMessages() {
//        return Result.builder().data(new String[]{"Message 1", "Message 2", "Message 3"}).build();
//    }
//
//    @GetMapping(value = "/callback")
//    public void callback(HttpServletRequest request) {
//        String code = request.getParameter("code");
//        String state = request.getParameter("state");
//        Map<String, String> map = new HashMap<>(5);
//        map.put("client_id", "db3bd3536a1d3163acf9");
//        map.put("client_secret", "2b3b4ae1eaf3a9590feb5f01c84b694019c69ce0");
//        map.put("code", code);
//        map.put("state", state);
//        RestTemplate restTemplate = new RestTemplate();
//        String s = restTemplate.postForObject("https://github.com/login/oauth/access_token", map, String.class);
//        log.warn("{}", request);
//    }
//
////    @RequestMapping("/login")
////    public Result login(HttpServletRequest request) {
//////        clientRegistrationRepository.
//////        ClientRegistration.withRegistrationId("").o
////        return Result.success("登陆");
////    }
//
////    @GetMapping
////    public Result home(HttpServletRequest request) {
////        return Result.success("首页");
////    }
//
//    @RequestMapping("/home")
//    public ModelAndView loginOAuth2(HttpServletRequest request) {
////        ModelAndView mv = new ModelAndView();
////        mv.setViewName("index");
//        throw new RuntimeException("test exception");
//    }
//}
