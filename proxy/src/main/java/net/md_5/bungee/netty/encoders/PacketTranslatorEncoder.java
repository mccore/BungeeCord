package net.md_5.bungee.netty.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.md_5.bungee.netty.PacketMapping;
import net.md_5.bungee.netty.Var;

@ChannelHandler.Sharable
public class PacketTranslatorEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        System.out.println( "ENCODING" );
        int packetId = in.readUnsignedShort();
        int mappedPacketId = PacketMapping.spm[ packetId ];
        System.out.println( mappedPacketId );
        Var.writeVarInt( mappedPacketId, out );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }
}
