package net.md_5.bungee.netty.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.md_5.bungee.netty.PacketMapping;

public class PacketTranslatorEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        int packetId = in.readUnsignedShort();
        int mappedPacketId = PacketMapping.spm[ packetId ];
        out.writeShort( mappedPacketId );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }
}
