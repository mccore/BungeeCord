package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class TabCompleteRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        Var.readString( in, false );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int length = Var.readVarInt( in );
        String[] result = new String[ length ];
        for ( int i = 0; i < length; i++ ) {
            result[ i ] = Var.readString( in, true );
        }
    }
}
