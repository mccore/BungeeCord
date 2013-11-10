package net.md_5.bungee.protocol.packet;

import io.netty.buffer.ByteBuf;

public class PacketLoginSuccess extends DefinedPacket {
    String name;
    public PacketLoginSuccess(String name) {
        super( 0xFC );
    }

    @Override
    public void read(ByteBuf buf) {

    }

    @Override
    public void write(ByteBuf buf) {
        writeString( "cake", buf );
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
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
