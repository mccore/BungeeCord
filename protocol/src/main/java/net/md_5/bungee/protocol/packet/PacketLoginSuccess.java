package net.md_5.bungee.protocol.packet;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class PacketLoginSuccess extends DefinedPacket {
    String name;
    public PacketLoginSuccess(String name) {
        super( 0xFC );
        this.name = name;
    }

    @Override
    public void read(ByteBuf buf) {

    }

    @Override
    public void write(ByteBuf buf) {
        writeString( UUID.randomUUID().toString(), buf );
        writeString( name, buf );
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception {

    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "UUID:" + "cake" + ", NAME:" + name;
    }
}
