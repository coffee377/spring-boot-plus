package com.voc.boot.socket.io.parser;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
final public class IOParser implements Parser {


    /*package*/ static class BinaryReconstructor {

        public Packet reconPack;

        /*package*/ List<byte[]> buffers;

        BinaryReconstructor(Packet packet) {
            this.reconPack = packet;
            this.buffers = new ArrayList<>();
        }

        public Packet takeBinaryData(byte[] binData) {
            this.buffers.add(binData);
            if (this.buffers.size() == this.reconPack.getAttachments()) {
                Packet packet = Binary.reconstructPacket(this.reconPack,
                        this.buffers.toArray(new byte[this.buffers.size()][]));
                this.finishReconstruction();
                return packet;
            }
            return null;
        }

        public void finishReconstruction() {
            this.reconPack = null;
            this.buffers = new ArrayList<>();
        }
    }
}
