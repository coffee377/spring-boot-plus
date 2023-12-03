package protocol;

import java.util.UUID;

/**
 * <a href="https://socket.io/zh-CN/docs/v4/engine-io-protocol/#handshake">handshake</a>
 */
public class Handshake {
    private final UUID sid;
    private final String[] upgrades;
    private final int pingInterval;
    private final int pingTimeout;
    private final int maxPayload;

    public Handshake(UUID sid, String[] upgrades, int pingInterval, int pingTimeout, int maxPayload) {
        this.sid = sid;
        this.upgrades = upgrades;
        this.pingInterval = pingInterval;
        this.pingTimeout = pingTimeout;
        this.maxPayload = maxPayload;
    }

    public UUID getSid() {
        return sid;
    }

    public String[] getUpgrades() {
        return upgrades;
    }

    public int getPingInterval() {
        return pingInterval;
    }

    public int getPingTimeout() {
        return pingTimeout;
    }

    public int getMaxPayload() {
        return maxPayload;
    }
}
