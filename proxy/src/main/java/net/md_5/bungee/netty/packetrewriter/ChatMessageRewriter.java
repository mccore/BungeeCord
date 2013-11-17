package net.md_5.bungee.netty.packetrewriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class ChatMessageRewriter extends PacketRewriter {
    JsonParser jsonParser = new JsonParser();
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        String message = Var.readString( in, true );
        Var.writeString( message, out, false );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        String message = Var.readString( in, false );
        JsonObject jObj = jsonParser.parse(message).getAsJsonObject();
        if ( jObj.has( "using" ) ) {
            JsonElement element = jObj.get( "using" );
            jObj.remove( "using" );
            jObj.add( "with", element );
        }
        Var.writeString( jObj.toString(), out, true );
    }
}
