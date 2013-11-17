package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class UseBedRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( false );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int entityId = in.readInt();
        in.skipBytes( 1 );
        out.writeInt( entityId );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
