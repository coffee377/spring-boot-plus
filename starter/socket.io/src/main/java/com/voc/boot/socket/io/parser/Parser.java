package com.voc.boot.socket.io.parser;

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.function.Consumer;

public interface Parser {

    interface Encoder {

        void encode(Packet packet, Callback callback);

        interface Callback {

            void call(Object[] data);
        }
    }

    interface Decoder<T> {

//        List<com.voc.boot.socket.io.parser.decode.Decoder<S, T>> getCallbacks();

        void add(ByteBuf content);

        @Deprecated
        void add(String obj);

        @Deprecated
        void add(byte[] obj);

        void destroy();

        /**
         * 解析完成
         *
         * @param consumer Consumer<T>
         */
        void onDecoded(Consumer<T> consumer);

    }
}
