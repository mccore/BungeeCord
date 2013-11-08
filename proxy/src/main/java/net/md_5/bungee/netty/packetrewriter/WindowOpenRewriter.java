package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class WindowOpenRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        byte windowId = in.readByte();
        byte inventoryType = in.readByte();
        String windowTitle = Var.readString( in, false );
        out.writeByte( windowId );
        out.writeByte( inventoryType );
        Var.writeString( windowTitle, out, true );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
