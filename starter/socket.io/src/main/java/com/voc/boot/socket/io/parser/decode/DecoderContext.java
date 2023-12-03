package com.voc.boot.socket.io.parser.decode;

public class DecoderContext<D, T> {

    private final D data;

    private final T tagret;

    public DecoderContext(D source, T tagret) {
        this.data = source;
        this.tagret = tagret;
    }

    public D getData() {
        return data;
    }

    public T geTagret() {
        return tagret;
    }
}
