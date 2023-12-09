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
package io.socket.engineio.handler;

import com.corundumstudio.socketio.messages.PacketsMessage;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Sharable
public class EngineIOPacketDecoder extends MessageToMessageDecoder<PacketsMessage> {
    @Override
    protected void decode(ChannelHandlerContext ctx, PacketsMessage msg, List<Object> out) throws Exception {

    }
}
