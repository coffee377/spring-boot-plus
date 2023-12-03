package com.voc.boot.socket.io.parser;

import io.netty.buffer.ByteBuf;

import java.util.function.Consumer;

public interface Parser {

    interface Encoder {

        void encode(Packet packet, Consumer<Object[]> consumer);

    }

    interface Decoder<T> {

        void add(String obj);

        void add(byte[] obj);

        void add(ByteBuf content);

        void destroy();

        /**
         * 解析完成
         *
         * @param consumer Consumer<T>
         */
        void onDecoded(Consumer<T> consumer);

    }
}
