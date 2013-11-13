package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class HeldItemChangeRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        byte slot = in.readByte();
        out.writeShort( slot );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        byte slot = (byte) in.readShort();
        out.writeByte( slot );
    }
}
