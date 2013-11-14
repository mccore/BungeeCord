package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class EntityInteractRewriter extends PacketRewriter {
    byte left = 1;
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        int target = in.readInt();
        byte button = in.readByte();
        out.writeInt( 1234 ); // Should be ignored by server, its from when the player sent their ID to the server
        out.writeInt( target );
        out.writeBoolean( button == left );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        unsupported( false );
    }
}
