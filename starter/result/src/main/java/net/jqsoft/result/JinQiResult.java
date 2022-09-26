package net.jqsoft.result;

import com.voc.boot.result.customizer.ResultPropertiesCustomizer;
import com.voc.boot.result.properties.JsonFieldProperties;
import com.voc.boot.result.properties.ResultProperties;
import org.springframework.core.Ordered;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/26 23:57
 */
public class JinQiResult implements ResultPropertiesCustomizer, Ordered {

    @Override
    public void customize(ResultProperties resultProperties) {
        resultProperties.getWrapper().setEnable(false);
        JsonFieldProperties json = resultProperties.getJson();
        json.setSuccess("succeed");
        json.setMessage("msg");
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
