package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class ChangeGameStateRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        byte reason = in.readByte();
        byte gameMode = in.readByte();
        out.writeByte( reason );
        out.writeFloat( gameMode );
    }
}
