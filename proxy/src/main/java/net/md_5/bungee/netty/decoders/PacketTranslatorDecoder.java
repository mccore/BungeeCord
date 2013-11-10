package net.md_5.bungee.netty.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import net.md_5.bungee.netty.PacketMapping;
import net.md_5.bungee.netty.PacketWrapper;
import net.md_5.bungee.netty.Var;
import net.md_5.bungee.netty.packetrewriter.PacketRewriter;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.packet.DefinedPacket;

import java.util.List;

public class PacketTranslatorDecoder extends ByteToMessageDecoder {

    // For the hack
    String user;
    String address;
    int serverPort;
    int nextState;


    boolean isInitialized = false;
    final static int STATUS = 1;
    final static int LOGIN = 2;

    Protocol protocol;
    public PacketTranslatorDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int packetId = Var.readVarInt( in );
        short mappedPacketId;
        if ( isInitialized ) {
            mappedPacketId = PacketMapping.cpm[ packetId ]; // Convert to 1.6.4 packet id
            System.out.println( packetId );
            PacketRewriter rewriter = PacketMapping.rewriters[ mappedPacketId ];
            ByteBuf content = Unpooled.buffer();
            content.writeByte( mappedPacketId );
            if ( rewriter == null ) {
                content.writeBytes( in.readBytes( in.readableBytes() ) );
            } else {
                rewriter.rewriteClientToServer( in, content );
            }
            ByteBuf copy = content.copy();
            PacketWrapper packet = new PacketWrapper( protocol.read( mappedPacketId, content ), copy );
            out.add( packet );
        } else {
            if ( packetId == 0x00 ) {
                if ( address == null ) {
                    Var.readVarInt( in ); // Ignore protocol version, cause we are pro like that.
                    address = Var.readString( in, true );
                    serverPort = in.readUnsignedShort();
                    nextState = Var.readVarInt( in );
                } else if ( nextState == LOGIN ) {
                    user = Var.readString( in, true );
                    ByteBuf handshake = Unpooled.buffer();
                    handshake.writeByte( 0x02 );
                    handshake.writeByte( 78 );
                    Var.writeString( user, handshake, false );
                    Var.writeString( address, handshake, false );
                    handshake.writeInt( serverPort );
                    ByteBuf copy = handshake.copy();
                    out.add( new PacketWrapper( protocol.read( handshake.readUnsignedByte(), handshake ), copy ) );
                }
            } else if ( packetId == 0x01 ) {
                ByteBuf response = Unpooled.buffer();
                response.writeByte( 0xFC );
                response.writeBytes( in.readBytes( in.readableBytes() ) );
                ByteBuf copy = response.copy();
                out.add( new PacketWrapper( protocol.read( response.readUnsignedByte(), response ), copy ) );
                System.out.println( "Wooo, encryption response! :D" );
                isInitialized = true;
            }
        }
    }
}
