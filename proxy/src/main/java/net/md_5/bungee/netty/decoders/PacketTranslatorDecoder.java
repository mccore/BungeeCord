package net.md_5.bungee.netty.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.md_5.bungee.netty.PacketMapping;
import net.md_5.bungee.netty.packetrewriter.PacketRewriter;
import net.md_5.bungee.protocol.packet.DefinedPacket;

public class PacketTranslatorDecoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        int packedId = DefinedPacket.readVarInt( in ); // Read packet id
        short mappedPacketId = PacketMapping.cpm[ packedId ]; // Convert to 1.6.4 packet id
        PacketRewriter rewriter = PacketMapping.rewriters[ mappedPacketId ];
        if ( rewriter == null ) {
            out.writeBytes( in.readBytes( in.readableBytes() ) );
        } else {
            rewriter.rewriteClientToServer( in, out );
        }
    }
}
