package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class SpawnMobRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int entityId = in.readInt();
        byte type = in.readByte();
        int x = in.readInt();
        int y = in.readInt();
        int z = in.readInt();
        byte pitch = in.readByte();
        byte headPitch = in.readByte();
        byte yaw = in.readByte();
        short velX = in.readShort();
        short velY = in.readShort();
        short velZ = in.readShort();

        Var.writeVarInt( entityId, out );
        out.writeByte( type );
        out.writeInt( x );
        out.writeInt( y );
        out.writeInt( z );
        out.writeByte( pitch );
        out.writeByte( headPitch );
        out.writeByte( yaw );
        out.writeShort( velX );
        out.writeShort( velY );
        out.writeShort( velZ );
        Var.rewriteEntityMetadata( in, out );
    }

}
