package io.socket.engineio;

import com.fasterxml.jackson.annotation.JsonValue;
import io.socket.engineio.transports.TransportName;

import java.util.Arrays;

public enum Transport implements TransportName {
    UNKNOWN("unknown"),
    POLLING("polling"),
    WEBSOCKET("websocket"),

    ;

    @JsonValue
    private final String value;
    public final static String PARAMETER_NAME = "transport";

    Transport(String value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return value;
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
