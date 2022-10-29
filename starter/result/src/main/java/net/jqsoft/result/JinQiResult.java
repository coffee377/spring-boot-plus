package net.jqsoft.result;

import com.voc.boot.result.customizer.ResultPropertiesCustomizer;
import com.voc.boot.result.properties.JsonField;
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
        JsonField json = resultProperties.getJson();
        json.setSuccess("succeed");
        json.setMessage("msg");
        resultProperties.setCodeAsNumber(true);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
