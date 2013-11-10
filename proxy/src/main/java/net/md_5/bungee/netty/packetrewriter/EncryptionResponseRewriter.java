package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class EncryptionResponseRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        Var.writeString( "cake", out, true );
        Var.writeString( "cake", out, true );
        in.readBytes( in.readableBytes() );
        // WOWE, such hack
    }
}
