package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class PositionAndLookRewriter extends PacketRewriter {
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        double x = in.readDouble();
        double y = in.readDouble();
        double stance = in.readDouble();
        double z = in.readDouble();
        float yaw = in.readFloat();
        float pitch = in.readFloat();
        boolean onGround = in.readBoolean();
        System.out.println( "x=" + x + ",y=" + y + ",z=" + z );
        out.writeDouble( x );
        out.writeDouble( y );
        out.writeDouble( stance );
        out.writeDouble( z );
        out.writeFloat( yaw );
        out.writeFloat( pitch );
        out.writeBoolean( onGround );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        double x = in.readDouble();
        in.readDouble(); // Ignore stance.
        double y = in.readDouble();
        double z = in.readDouble();
        out.writeDouble( x );
        out.writeDouble( y );
        out.writeDouble( z );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }
}
