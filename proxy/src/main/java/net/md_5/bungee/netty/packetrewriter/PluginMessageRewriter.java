package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

import java.nio.charset.Charset;

public class PluginMessageRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        String channel = Var.readString( in, true );
        short length = in.readShort();
        byte[] content = new byte[ length ];
        in.readBytes(content);

        System.out.println( channel );
        System.out.println( new String( content, Charset.forName( "UTF-8" ) ) );

        Var.writeString( channel, out, false );
        out.writeShort( length );
        out.writeBytes( content );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        String channel = Var.readString( in, false );
        Var.writeString( channel, out, true );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
