package io.socket.engineio.handler;

import io.socket.engineio.Transport;

/**
 * <a href="https://socket.io/zh-CN/docs/v4/engine-io-protocol/#handshake">handshake</a>
 */
public class HandshakeResponse<T> {
    /**
     * The session ID.
     */
    private final T sid;
    /**
     * The list of available transport upgrades
     */
    private final Transport[] upgrades;
    /**
     * The ping interval, used in the heartbeat mechanism (in milliseconds)
     */
    private final int pingInterval;
    /**
     * The ping timeout, used in the heartbeat mechanism (in milliseconds)
     */
    private final int pingTimeout;
    /**
     * The maximum number of bytes per chunk, used by the client to aggregate packets into payloads
     */
    private final int maxPayload;

    public HandshakeResponse(T sid) {
        this(sid, new Transport[]{Transport.WEBSOCKET}, 25000, 20000, 1000000);
    }

    public HandshakeResponse(T sid, Transport[] upgrades) {
        this(sid, upgrades, 65000, 60000, 1000000);
    }

    public HandshakeResponse(T sid, Transport[] upgrades, int pingInterval, int pingTimeout, int maxPayload) {
        this.sid = sid;
        this.upgrades = upgrades;
        this.pingInterval = pingInterval;
        this.pingTimeout = pingTimeout;
        this.maxPayload = maxPayload;
    }

    public T getSid() {
        return sid;
    }

    public Transport[] getUpgrades() {
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
