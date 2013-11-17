package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class ClientSettingsRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        String locale = Var.readString( in, true );
        byte viewDistance = in.readByte();
        byte chatFlags = in.readByte();
        in.readBoolean();
        byte difficulty = in.readByte();
        boolean showCape = in.readBoolean();

        Var.writeString( locale, out, false );
        out.writeByte( viewDistance );
        out.writeByte( chatFlags );
        out.writeByte( difficulty );
        out.writeBoolean( showCape );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        unsupported( false );
    }

}
