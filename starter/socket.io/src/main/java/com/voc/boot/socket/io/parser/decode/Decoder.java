package com.voc.boot.socket.io.parser.decode;

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public interface Decoder<D extends ByteBuf, T> extends Predicate<DecoderContext<D, T>> {

    default void add(D data) {

    }


    default void destroy() {

    }

    /**
     * 解析完成
     *
     * @param consumer Consumer<T>
     */
    default void onDecoded(Consumer<T> consumer) {

    }

    default List<Decoder<D, T>> getDecoders() {
        return null;
    }

    default void decode(DecoderContext<D, T> context) {
        if (getDecoders() != null && !getDecoders().isEmpty()) {
            getDecoders().stream()
                    .filter(decoder -> decoder.test(context)).
                    forEach(decoder -> decoder.decode(context));
        }
    }

    @Override
    default boolean test(DecoderContext<D, T> context) {
        return context.getData().isReadable();
    }
}
