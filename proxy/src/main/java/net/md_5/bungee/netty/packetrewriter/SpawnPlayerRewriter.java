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
        int x = in.readInt();
        int y = in.readInt();
        int z = in.readInt();
        byte yaw = in.readByte();
        byte pitch = in.readByte();
        short currentItem = in.readShort();

        Var.writeVarInt( entityId, out );
        Var.writeString( "wowe_such_packet", out, true );
        Var.writeString( name, out, true );
        out.writeInt( x );
        out.writeInt( y );
        out.writeInt( z );
        out.writeByte( yaw );
        out.writeByte( pitch );
        out.writeShort( currentItem );
        Var.rewriteEntityMetadata( in, out );
    }

}
