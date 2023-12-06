/**
 * Copyright (c) 2012-2023 Nikita Koksharov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corundumstudio.socketio.handler;

import com.corundumstudio.socketio.listener.ExceptionListener;
import com.corundumstudio.socketio.messages.PacketsMessage;
import com.corundumstudio.socketio.namespace.Namespace;
import com.corundumstudio.socketio.namespace.NamespacesHub;
import com.corundumstudio.socketio.protocol.*;
import com.corundumstudio.socketio.transport.NamespaceClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
public class InPacketHandler extends SimpleChannelInboundHandler<PacketsMessage> {

    private static final Logger log = LoggerFactory.getLogger(InPacketHandler.class);

    private final PacketListener packetListener;
    private final PacketDecoder decoder;
    private final NamespacesHub namespacesHub;
    private final ExceptionListener exceptionListener;

    public InPacketHandler(PacketListener packetListener, PacketDecoder decoder, NamespacesHub namespacesHub, ExceptionListener exceptionListener) {
        super();
        this.packetListener = packetListener;
        this.decoder = decoder;
        this.namespacesHub = namespacesHub;
        this.exceptionListener = exceptionListener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PacketsMessage message)
                throws Exception {
        ByteBuf content = message.getContent();
        ClientHead client = message.getClient();

        String data = content.toString(CharsetUtil.UTF_8);
        if (log.isTraceEnabled()) {
            log.trace("In message: {} sessionId: {}", data, client.getSessionId());
        }

        while (content.isReadable()) {
            try {
                // todo 40/test,{"token":"test"} 换成自定义解析
                // todo 自定义解析 PacketsMessage 40/test,{"token":"test"}
                Packet packet = decoder.decodePackets(content, client);

                Namespace ns = namespacesHub.get(packet.getNsp());
                if (ns == null) {
                    if (packet.getSubType() == PacketType.CONNECT) {
                        Packet p = new Packet(PacketType.MESSAGE, client.getEngineIOVersion());
                        p.setSubType(PacketType.CONNECT_ERROR);
                        p.setNsp(packet.getNsp());

                        if (client.getEngineIOVersion().equals(EngineIOVersion.V2)) {
                            p.setData("Invalid namespace");
                        } else {
                            p.setData(new ConnErrorPacket("Invalid namespace"));
                        }

                        client.send(p);
                        return;
                    }
                    log.debug("Can't find namespace for endpoint: {}, sessionId: {} probably it was removed.", packet.getNsp(), client.getSessionId());
                    return;
                }

                if (packet.getSubType() == PacketType.CONNECT) {
                    client.addNamespaceClient(ns);
                    //:TODO lyjnew client namespace send connect packet 0+namespace  socket io v4
                    // https://socket.io/docs/v4/socket-io-protocol/#connection-to-a-namespace
                    if (EngineIOVersion.V4.equals(client.getEngineIOVersion())) {
                        Packet p = new Packet(PacketType.MESSAGE, client.getEngineIOVersion());
                        p.setSubType(PacketType.CONNECT);
                        p.setNsp(packet.getNsp());
                        p.setData(new ConnPacket(client.getSessionId()));
                        client.send(p);
                    }
                }

                NamespaceClient nClient = client.getChildClient(ns);
                if (nClient == null) {
                    log.debug("Can't find namespace client in namespace: {}, sessionId: {} probably it was disconnected.", ns.getName(), client.getSessionId());
                    return;
                }
                if (packet.hasAttachments() && !packet.isAttachmentsLoaded()) {
                    return;
                }
                packetListener.onPacket(packet, nClient, message.getTransport());
            } catch (Exception ex) {
                String c = content.toString(CharsetUtil.UTF_8);
                log.error("Error during data processing. Client sessionId: " + client.getSessionId() + ", data: " + c, ex);
                throw ex;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        if (!exceptionListener.exceptionCaught(ctx, e)) {
            super.exceptionCaught(ctx, e);
        }
    }

}
