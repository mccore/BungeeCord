package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class UpdateSignRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        int x = in.readInt();
        short y = in.readShort();
        int z = in.readInt();
        String line1 = Var.readString( in, true );
        String line2 = Var.readString( in, true );
        String line3 = Var.readString( in, true );
        String line4 = Var.readString( in, true );
        out.writeInt( x );
        out.writeShort( y );
        out.writeInt( z );
        Var.writeString( line1, out, false );
        Var.writeString( line2, out, false );
        Var.writeString( line3, out, false );
        Var.writeString( line4, out, false );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int x = in.readInt();
        short y = in.readShort();
        int z = in.readInt();
        String line1 = Var.readString( in, false );
        String line2 = Var.readString( in, false );
        String line3 = Var.readString( in, false );
        String line4 = Var.readString( in, false );
        out.writeInt( x );
        out.writeShort( y );
        out.writeInt( z );
        Var.writeString( line1, out, true );
        Var.writeString( line2, out, true );
        Var.writeString( line3, out, true );
        Var.writeString( line4, out, true );
    }
}
