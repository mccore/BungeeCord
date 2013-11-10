package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class HandshakeRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        int protocolVersion = readVarInt(in);
        String host = Var.readString( in, true );
        int port = readVarInt( in );
        readVarInt( in ); // Size of the login packet
        readVarInt( in ); // Packet ID to the login packet
        String user = readString( in );
        out.writeByte( 78 );
        Var.writeString( user, out, false );
        Var.writeString(host, out, false);
        out.writeInt( port );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }
}
