package net.md_5.bungee.netty.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.netty.encoders.PacketTranslatorEncoder;
import net.md_5.bungee.netty.encoders.Varint21LengthFieldPrepender;

public class InitialPacketDecoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        short packetId = in.readShort();
        ctx.pipeline().remove( this );
        if ( packetId != 0x02 ) {
            ctx.pipeline().addAfter( PipelineUtils.TIMEOUT_HANDLER, PipelineUtils.VARINT_DECODE_HANDLER, new Varint21FrameDecoder() );
            ctx.pipeline().addAfter( PipelineUtils.VARINT_DECODE_HANDLER, PipelineUtils.TRANSLATOR_DECODE_HANDLER, new PacketTranslatorDecoder() );
            ctx.pipeline().addAfter( PipelineUtils.PACKET_ENCODE_HANDLER, PipelineUtils.TRANSLATOR_ENCODE_HANDLER, new PacketTranslatorEncoder() );
            ctx.pipeline().addAfter( PipelineUtils.TRANSLATOR_ENCODE_HANDLER, PipelineUtils.VARINT_ENCODE_HANDLER, new Varint21LengthFieldPrepender() );
        }
        in.resetReaderIndex();
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }
}
