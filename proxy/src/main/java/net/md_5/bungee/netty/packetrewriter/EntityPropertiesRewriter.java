package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class EntityPropertiesRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int entityId = in.readInt();
        int propertyCount = in.readInt();
        out.writeInt( entityId );
        out.writeInt( propertyCount );
        for ( int i = 0; i < propertyCount; i++ ) {
            String key = Var.readString( in, false );
            double value = in.readDouble();
            short length = in.readShort();
            Var.writeString( key, out, true );
            out.writeDouble( value );
            out.writeShort( length );
            for ( int j = 0; j < length; j++ ) {
                byte[] uuid = new byte[ 16 ];
                in.readBytes( uuid );
                double amount = in.readDouble();
                byte operation = in.readByte();
                out.writeBytes( uuid );
                out.writeDouble( amount );
                out.writeByte( operation );
            }
        }
    }
}
