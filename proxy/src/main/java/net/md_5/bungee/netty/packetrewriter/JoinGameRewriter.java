package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class JoinGameRewriter extends PacketRewriter {

    /*
    Packet ID 	    Field Name      Field Type
    0x01 	        Entity ID 	    int
                    Level type 	    string
                    Game mode 	    byte
                    Dimension 	    byte
                    Difficulty 	    byte
                    Not used 	    byte
                    Max players 	byte
                    Total Size: 	12 bytes + length of strings

    Packet ID       Entity ID       Int
    0x01            Gamemode        Unsigned Byte
                    Dimension       Byte
                    Difficulty 	    Unsigned Byte
                    Max Players     Unsigned Byte
                    Level Type 	    String
     */

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int entityId = in.readInt();
        String levelType = Var.readString( in, false );
        byte gameMode = in.readByte();
        byte dimention = in.readByte();
        byte difficulty = in.readByte();
        in.readByte();
        byte maxPlayers = in.readByte();

        out.writeInt( entityId );
        out.writeByte( gameMode );
        out.writeByte( dimention );
        out.writeByte( difficulty );
        out.writeByte( maxPlayers );
        Var.writeString( levelType, out, true );
    }

}
