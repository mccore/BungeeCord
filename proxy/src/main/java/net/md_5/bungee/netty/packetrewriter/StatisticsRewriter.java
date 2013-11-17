package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class StatisticsRewriter extends PacketRewriter {

    public static String[] statisticsMapping = new String[ 2029 ];
    static {
        statisticsMapping[ 1004 ] = "stat.leaveGame";
        statisticsMapping[ 1100 ] = "stat.playOneMinute";
        statisticsMapping[ 2000 ] = "stat.walkOneCm";
        statisticsMapping[ 2001 ] = "stat.swimOneCm";
        statisticsMapping[ 2002 ] = "stat.fallOneCm";
        statisticsMapping[ 2003 ] = "stat.climbOneCm";
        statisticsMapping[ 2004 ] = "stat.flyOneCm";
        statisticsMapping[ 2005 ] = "stat.diveOneCm";
        statisticsMapping[ 2006 ] = "stat.minecartOneCm";
        statisticsMapping[ 2007 ] = "stat.boatOneCm";
        statisticsMapping[ 2008 ] = "stat.pigOneCm"; // Not on minecraftwiki
        statisticsMapping[ 2009 ] = "stat.horseOneCm"; // Same with this one
        statisticsMapping[ 2010 ] = "stat.jump";
        statisticsMapping[ 2011 ] = "stat.drop";
        statisticsMapping[ 2020 ] = "stat.damageDealt";
        statisticsMapping[ 2021 ] = "stat.damageTaken";
        statisticsMapping[ 2022 ] = "stat.deaths";
        statisticsMapping[ 2023 ] = "stat.mobKills";
        statisticsMapping[ 2024 ] = "stat.playerKills";
        statisticsMapping[ 2025 ] = "stat.fishCaught";
        statisticsMapping[ 2026 ] = "stat.junkFished";
        statisticsMapping[ 2027 ] = "stat.treasureFished";
        statisticsMapping[ 2028 ] = "stat.animalsBred"; // Not on wiki, and no really fitting ids
    }

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out) {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out) {
        int id = in.readInt();
        int amount = in.readInt();
        Var.writeVarInt( 1, out );
        Var.writeString( statisticsMapping[ id ], out, true );
        Var.writeVarInt( amount, out );
    }
}
