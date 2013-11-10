package net.md_5.bungee.netty;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.packetrewriter.*;
import net.md_5.bungee.protocol.packet.DefinedPacket;

public class PacketMapping {
    /**
     * Client packet mapping - packets from client to server
      */
    public static short[] cpm = new short[ 0x18 ];
    /**
     * Server packet mapping - packets from server to client
      */
    public static int[] spm = new int[ 0xFF ];

    /**
     * Re-writers for packets
      */
    public static PacketRewriter[] rewriters = new PacketRewriter[ 0xFF + 1 ];

    static {
        // Client mappings
        cpm[ 0x00 ] = 0x00;
        cpm[ 0x01 ] = 0x03;
        cpm[ 0x02 ] = 0x07;
        cpm[ 0x03 ] = 0x0A;
        cpm[ 0x04 ] = 0x0B;
        cpm[ 0x05 ] = 0x0C;
        cpm[ 0x06 ] = 0x0D;
        cpm[ 0x07 ] = 0x0E;
        cpm[ 0x08 ] = 0x0F;
        cpm[ 0x09 ] = 0x10;
        cpm[ 0x0A ] = 0x12;
        cpm[ 0x0B ] = 0x13;
        cpm[ 0x0C ] = 0x1B;
        cpm[ 0x0D ] = 0x65;
        cpm[ 0x0E ] = 0x66;
        cpm[ 0x0F ] = 0x6A;
        cpm[ 0x10 ] = 0x6B;
        cpm[ 0x11 ] = 0x6C;
        cpm[ 0x12 ] = 0x82;
        cpm[ 0x13 ] = 0xCA;
        cpm[ 0x14 ] = 0xCB;
        cpm[ 0x15 ] = 0xCC;
        cpm[ 0x16 ] = 0xCD;
        cpm[ 0x17 ] = 0xFA;

        // Server mappings
        spm[ 0x00 ] = 0x00;
        spm[ 0x01 ] = 0x01;
        spm[ 0x02 ] = 0x02;
        spm[ 0x03 ] = 0x04;
        spm[ 0x04 ] = 0x05;
        spm[ 0x05 ] = 0x06;
        spm[ 0x06 ] = 0x08;
        spm[ 0x07 ] = 0x09;
        spm[ 0x08 ] = 0x0D;
        spm[ 0x09 ] = 0x10;
        spm[ 0x0A ] = 0x11;
        spm[ 0x0B ] = 0x12;
        spm[ 0x0C ] = 0x14;
        spm[ 0x0D ] = 0x16;
        spm[ 0x0E ] = 0x17;
        spm[ 0x0F ] = 0x18;
        spm[ 0x10 ] = 0x19;
        spm[ 0x11 ] = 0x1A;
        spm[ 0x12 ] = 0x1C;
        spm[ 0x13 ] = 0x1D;
        spm[ 0x14 ] = 0x1E;
        spm[ 0x15 ] = 0x1F;
        spm[ 0x16 ] = 0x20;
        spm[ 0x17 ] = 0x21;
        spm[ 0x18 ] = 0x22;
        spm[ 0x19 ] = 0x23;
        spm[ 0x1A ] = 0x26;
        spm[ 0x1B ] = 0x27;
        spm[ 0x1C ] = 0x28;
        spm[ 0x1D ] = 0x29;
        spm[ 0x1E ] = 0x2A;
        spm[ 0x1F ] = 0x2B;
        spm[ 0x20 ] = 0x2C;
        spm[ 0x21 ] = 0x33;
        spm[ 0x22 ] = 0x34;
        spm[ 0x23 ] = 0x35;
        spm[ 0x24 ] = 0x36;
        spm[ 0x25 ] = 0x37;
        spm[ 0x26 ] = 0x38;
        spm[ 0x27 ] = 0x3C;
        spm[ 0x28 ] = 0x3D;
        spm[ 0x29 ] = 0x3E;
        spm[ 0x2A ] = 0x3F;
        spm[ 0x2B ] = 0x46;
        spm[ 0x2C ] = 0x47;
        spm[ 0x2D ] = 0x64;
        spm[ 0x2E ] = 0x65;
        spm[ 0x2F ] = 0x67;
        spm[ 0x30 ] = 0x68;
        spm[ 0x31 ] = 0x69;
        spm[ 0x32 ] = 0x6A;
        spm[ 0x33 ] = 0x82;
        spm[ 0x34 ] = 0x83;
        spm[ 0x35 ] = 0x84;
        spm[ 0x36 ] = 0x85;
        spm[ 0x37 ] = 0xC8;
        spm[ 0x38 ] = 0xC9;
        spm[ 0x39 ] = 0xCA;
        spm[ 0x3A ] = 0xCB;
        spm[ 0x3B ] = 0xCB;
        spm[ 0x3C ] = 0xCF;
        spm[ 0x3D ] = 0xD0;
        spm[ 0x3E ] = 0xD1;
        spm[ 0x3F ] = 0xFA;
        spm[ 0x40 ] = 0xFF;

        // Re-writers
        rewriters[ 0x01 ] = new JoinGameRewriter();
        rewriters[ 0x02 ] = new HandshakeRewriter();
        rewriters[ 0x03 ] = new ChatMessageRewriter();
        rewriters[ 0x09 ] = new RespawnRewriter();
        rewriters[ 0x0B ] = new EntityChangeRewriter();
        rewriters[ 0x14 ] = new SpawnPlayerRewriter();
        rewriters[ 0x17 ] = new EntityChangeRewriter();
        rewriters[ 0x18 ] = new EntityChangeRewriter();
        rewriters[ 0x19 ] = new SpawnPaintingRewriter();
        rewriters[ 0x1A ] = new EntityChangeRewriter();

        // TODO 0x2C

        rewriters[ 0x35 ] = new BlockChangeRewriter();
        rewriters[ 0x36 ] = new BlockActionRewriter();
        rewriters[ 0x37 ] = new EntityChangeRewriter();
        rewriters[ 0x3E ] = new SoundEffectRewriter();
        rewriters[ 0x3F ] = new ParticleRewriter();
        rewriters[ 0x64 ] = new WindowOpenRewriter();
        rewriters[ 0x47 ] = new EntityChangeRewriter();
        rewriters[ 0x82 ] = new UpdateSignRewriter();
        rewriters[ 0x83 ] = new EntityChangeRewriter(); // Not really entity change, but first value is int
        rewriters[ 0xC8 ] = new StatiticsRewriter();
        rewriters[ 0xC9 ] = new PlayerListItemRewriter();
        rewriters[ 0xCB ] = new TabCompleteRewriter();
        rewriters[ 0xCC ] = new ClientSettingsRewriter();
        rewriters[ 0xCE ] = new ScoreboardObjectiveRewriter();
        rewriters[ 0xCF ] = new UpdateScoreRewriter();
        rewriters[ 0xD0 ] = new ShowScoreboardRewriter();
        rewriters[ 0xD1 ] = new TeamsRewriter();
        rewriters[ 0xFA ] = new PluginMessageRewriter();
        rewriters[ 0xFC ] = new EncryptionRequestRewriter();
        rewriters[ 0xFF ] = new DisconnectRewriter();
    }
}
