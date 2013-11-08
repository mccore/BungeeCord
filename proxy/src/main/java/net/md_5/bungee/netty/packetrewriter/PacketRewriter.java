package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public abstract class PacketRewriter {
    public abstract void rewriteClientToServer(ByteBuf in, ByteBuf out);
    public abstract void rewriteServerToClient(ByteBuf in, ByteBuf out);


    public void writeString(String s, ByteBuf buf)
    {
        // TODO: Check len - use Guava?
        buf.writeShort( s.length() );
        for ( char c : s.toCharArray() )
        {
            buf.writeChar( c );
        }
    }

    public String readString(ByteBuf buf)
    {
        // TODO: Check len - use Guava?
        short len = buf.readShort();
        char[] chars = new char[ len ];
        for ( int i = 0; i < len; i++ )
        {
            chars[i] = buf.readChar();
        }
        return new String( chars );
    }

    public void writeArray(byte[] b, ByteBuf buf)
    {
        // TODO: Check len - use Guava?
        buf.writeShort( b.length );
        buf.writeBytes( b );
    }

    public byte[] readArray(ByteBuf buf)
    {
        // TODO: Check len - use Guava?
        short len = buf.readShort();
        byte[] ret = new byte[ len ];
        buf.readBytes( ret );
        return ret;
    }

    public static int readVarInt(ByteBuf input)
    {
        int out = 0;
        int bytes = 0;
        byte in;
        while ( true )
        {
            in = input.readByte();

            out |= ( in & 0x7F ) << ( bytes++ * 7 );

            if ( bytes > 32 )
            {
                throw new RuntimeException( "VarInt too big" );
            }

            if ( ( in & 0x80 ) != 0x80 )
            {
                break;
            }
        }
        return out;
    }

    public static void writeVarInt(int value, ByteBuf output)
    {
        int part;
        while ( true )
        {
            part = value & 0x7F;

            value >>>= 7;
            if ( value != 0 )
            {
                part |= 0x80;
            }

            output.writeByte( part );

            if ( value == 0 )
            {
                break;
            }
        }
    }

    public void unsupported(boolean clientside) throws UnsupportedOperationException {
        if ( clientside ) {
            throw new UnsupportedOperationException( "This packet is only client to server.");
        } else {
            throw new UnsupportedOperationException( "This packet is only server to client." );
        }
    }
}
