package api.bean;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 16:03
 */
public interface IUpdate {
    /**
     * 获取更新人
     *
     * @return String
     */
    String getUpdatedBy();

    /**
     * 设置更新人
     *
     * @param updater 更新人 ID
     */
    void setUpdatedBy(String updater);

    /**
     * 获取更新时间
     *
     * @return Instant
     */
    Instant getUpdatedAt();

    /**
     * 设置更新时间
     *
     * @param time 更新时间
     */
    void setUpdatedAt(Instant time);

}
