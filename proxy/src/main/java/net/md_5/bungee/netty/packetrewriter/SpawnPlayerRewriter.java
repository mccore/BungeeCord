package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

import java.util.UUID;

public class SpawnPlayerRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int entityId = in.readInt();
        String name = Var.readString( in, false );

        Var.writeString( "wowe_such_packet", out, true );
        Var.writeString( name, out, true );
        Var.writeVarInt( entityId, out );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
