package io.socket.engineio.transports;

public interface TransportName {

    /**
     * Get the name of this transport.
     *
     * @return Name of transport.
     */
    String getName();

//    /**
//     * 获取 EngineIO 协议版本
//     *
//     * @return version of the protocol.
//     */
//    default Version getEngineIOVersion() {
//        Map<String, String> initialQuery = getInitialQuery();
//        String eio = initialQuery.get(Version.PARAMETER_NAME);
//        return Version.from(eio);
//    }
//
//    default String getTransport() {
//        return getInitialQuery().get("transport");
//    }
//
//    default Parser getParser(Version version) {
//        if (Version.V3.equals(version)) {
//            return Parser.PROTOCOL_V3;
//        }
//        return Parser.PROTOCOL_V4;
//    }
//
//
//    /**
//     * Get the query parameters of the initial HTTP request.
//     *
//     * @return Query parameters of the initial HTTP request.
//     */
//    Map<String, String> getInitialQuery();
//
//    /**
//     * Get the headers of the initial HTTP request.
//     *
//     * @return Headers of the initial HTTP request.
//     */
//    Map<String, List<String>> getInitialHeaders();
//
//    /**
//     * Send a list of packets over the transport.
//     *
//     * @param packets List of packets to send.
//     */
//    void send(List<Packet<?>> packets);
//
//    /**
//     * Called by child class to indicate error with transport.
//     *
//     * @param reason      Reason of error.
//     * @param description Description of error.
//     */
//    default void onError(String reason, String description) {
////        if(!this.listeners("error").isEmpty()) {
////            emit("error", reason, description);
////        }
//    }
//
//    /**
//     * Called by child to indicate a packet receive from remote client.
//     *
//     * @param packet Packet received by transport.
//     */
//    default void onPacket(Packet<?> packet) {
////        emit("packet", packet);
//    }
//
//    /**
//     * Called by child to indicate data received from remote client.
//     *
//     * @param data Encoded data received by transport.
//     */
//    default void onData(Object data) {
//        Version version = getEngineIOVersion();
//        Parser mParser = getParser(version);
//        if (Version.UNKNOWN.equals(version)) {
//            onError("", "");
//            return;
//        }
//        onPacket(mParser.decodePacket(data));
//    }
//
//    /**
//     * Called by child to indicate closure of transport.
//     */
//    default void onClose() {
////        mReadyState = ReadyState.CLOSED;
////        emit("close");
//    }

}
