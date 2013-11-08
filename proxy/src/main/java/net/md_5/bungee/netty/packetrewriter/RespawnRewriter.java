package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class RespawnRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int dimention = in.readInt();
        byte difficulty = in.readByte();
        byte gamemode = in.readByte();
        in.readShort(); // Ignore world height
        String levelType = Var.readString( in, false );

        out.writeInt( dimention );
        out.writeByte( difficulty );
        out.writeByte( gamemode );
        Var.writeString( levelType, out, true );
    }

}
