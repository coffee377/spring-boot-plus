package com.voc.security.core.authentication.qrcode;

import com.voc.security.core.authentication.IUser;
import com.voc.security.core.authentication.DefaultUserDetails;
import com.voc.security.core.expection.UnboundUserException;
import com.voc.security.core.third.ThirdApp;
import com.voc.security.core.third.ThirdAppService;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/23 15:07
 */
@Getter
public class QRAuthenticationUserDetailsService implements AuthenticationUserDetailsService<QRAuthenticationToken>, InitializingBean {
    private ThirdAppService thirdAppService;

    public void setThirdAppService(ThirdAppService thirdAppService) {
        this.thirdAppService = thirdAppService;
    }

    @Override
    public UserDetails loadUserDetails(QRAuthenticationToken token) throws UsernameNotFoundException {
        ThirdApp app = thirdAppService.getUserInfoByClientIdAndTmpAuthCode(token.getClientId(), token.getCode());
//        AuthService userService = SpringUtils.getBean(AuthService.class);
//        IUser user = userService.getUserByThirdApp(app);
        IUser user = null;
        if (user == null) {
            String message = String.format("the current app %s is not bound to the system user with unionid=%s and " +
                    "openid=%s", app.getType().get(), app.getUnionid(), app.getUnionid());
            throw new UnboundUserException(message);
        }
        return new DefaultUserDetails(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(thirdAppService, "thirdAppService must not be null");
    }

}
