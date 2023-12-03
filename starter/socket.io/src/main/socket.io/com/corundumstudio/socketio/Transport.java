/**
 * Copyright (c) 2012-2023 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corundumstudio.socketio;

import com.corundumstudio.socketio.transport.WebSocketTransport;
import com.corundumstudio.socketio.transport.PollingTransport;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Transport {
    UNKNOWN("unknown"),

    WEBSOCKET(WebSocketTransport.NAME),
    POLLING(PollingTransport.NAME);

    @JsonValue
    private final String value;
    public final static String PARAMETER_NAME = "transport";

    Transport(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Transport from(String value) {
        return Arrays.stream(values())
                .filter(version -> value.equalsIgnoreCase(version.getValue()))
                .findFirst().orElse(UNKNOWN);
    }
}
