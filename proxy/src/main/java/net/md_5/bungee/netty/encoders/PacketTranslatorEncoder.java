package net.md_5.bungee.netty.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.md_5.bungee.connection.CancelSendSignal;
import net.md_5.bungee.netty.PacketMapping;
import net.md_5.bungee.netty.Var;
import net.md_5.bungee.netty.packetrewriter.PacketRewriter;

@ChannelHandler.Sharable
public class PacketTranslatorEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        short packetId = msg.readUnsignedByte();
        PacketRewriter rewriter = PacketMapping.rewriters[ packetId ];
        int mappedPacketId = PacketMapping.spm[ packetId ];
        Var.writeVarInt( mappedPacketId, out );
        if ( rewriter == null ) {
            out.writeBytes( msg.readBytes( msg.readableBytes() ) );
        } else {
            rewriter.rewriteServerToClient( msg, out );
        }
    }
}
