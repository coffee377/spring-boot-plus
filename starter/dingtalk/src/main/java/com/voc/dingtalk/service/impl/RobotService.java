//package com.voc.dingtalk.service.impl;
//
//import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
//import com.dingtalk.api.request.OapiRobotSendRequest;
//import com.voc.dingtalk.autoconfigure.model.App;
//import com.voc.dingtalk.service.IAppService;
//import com.voc.dingtalk.service.IRobotService;
//import com.voc.dingtalk.url.Notification;
//import com.voc.dingtalk.url.Robot;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2022/05/01 11:58
// */
//@Service
//public class RobotService implements IRobotService {
//    @Resource
//    private IAppService appService;
//
//    @Override
//    public void sendWebhookMessage(String accessToken, String msgType, Object content) {
//        OapiRobotSendRequest request = new OapiRobotSendRequest();
//        request.setMsgtype(msgType);
//        switch (msgType) {
//            case "link":
//                OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//                link.setTitle("Link消息标题");
//                link.setText("这是Link消息测试");
//                link.setMessageUrl("https://open.dingtalk.com/document/");
//                link.setPicUrl("https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png"); // 可选
//                request.setLink(link);
//            case "markdown":
//            case "actionCard":
//            case "feedCard":
//            default:
//            case "text":
//                OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//                text.setContent(!StringUtils.hasText(content.toString()) ? "测试内容" : content.toString());
//                request.setText(text);
//        }
//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setIsAtAll(false);
//        request.setAt(at);
//        execute(Robot.ROBOT_SEND_MESSAGE, request, accessToken, res -> {
//        });
//    }
//
//    @Override
//    public void sendMessage(String msgType, Object content, List<String> dingUserIds){
//        String accessToken = appService.getAccessToken("teamwork");
//        App app = appService.getByIdOrName("teamwork");
//
//        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
//        request.setAgentId(Long.parseLong(app.getAgentId()));
//        String usrIdList = StringUtils.collectionToCommaDelimitedString(dingUserIds);
//        request.setUseridList(usrIdList);
//        request.setToAllUser(false);
//
//        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
//        msg.setMsgtype(msgType);
//        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
//        msg.getText().setContent(!StringUtils.hasText(content.toString()) ? "测试内容" : content.toString());
//        request.setMsg(msg);
//        execute(Notification.WORD_SEND_MESSAGE, request, accessToken, res -> {
//        });
//
//    }
//
//
//}
