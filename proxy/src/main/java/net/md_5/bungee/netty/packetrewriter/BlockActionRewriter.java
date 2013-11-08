package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class BlockActionRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int x = in.readInt();
        short y = in.readShort();
        int z = in.readInt();
        byte b1 = in.readByte();
        byte b2 = in.readByte();
        int blockType = Var.readVarInt( in );
        out.writeInt( x );
        out.writeShort( y );
        out.writeInt( z );
        out.writeByte( b1 );
        out.writeByte( b2 );
        out.writeShort( blockType );
    }

}
