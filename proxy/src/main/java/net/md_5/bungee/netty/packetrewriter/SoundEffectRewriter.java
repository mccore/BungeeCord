package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class SoundEffectRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        String soundname = Var.readString( in, false );
        Var.writeString( soundname, out, true );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
