package io.socket.engineio;

import java.util.List;

public interface Emit {

    Emit on(String event, Listener listener);

    Emit once(String event, Listener listener);

    Emit off();

    Emit off(String event);

    Emit off(String event,Listener listener);

    Emit emit(String event, Object... args);

    List<Listener> listeners(String event);

    boolean hasListeners(String event);

    Emit removeListener(String event, Listener listener);

    Emit removeAlListener(String event);

    Emit removeAllListener();
}
