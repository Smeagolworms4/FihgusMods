package fihgu.common.events;

import java.util.ArrayList;

import fihgu.login.CommonProxy;
import fihgu.login.commands.LoginCommand;
import fihgu.login.commands.RegisterCommand;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.Packet16BlockItemSwitch;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet201PlayerInfo;
import net.minecraft.network.packet.Packet202PlayerAbilities;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet4UpdateTime;
import net.minecraft.network.packet.Packet6SpawnPosition;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ForgeSubscribe;
import fihgu.common.commands.FlyCommand;
import fihgu.core.events.PlayerLoginEvent;
import fihgu.core.events.PlayerLogoutEvent;
import fihgu.core.events.TryCommandEvent;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
import fihgu.core.io.SaveFile;
import fihgu.core.shortcut.FML;
import fihgu.core.shortcut.Server;
import cpw.mods.fml.common.registry.GameRegistry;

public class EventLoginHandler 
{
	@ForgeSubscribe
	public void onPlayerLogin(PlayerLoginEvent e)
	{
		if (FlyCommand.mustFlying(e.player)) {
			e.player.capabilities.allowFlying = true;
			e.player.sendPlayerAbilities();
			System.out.println ("FLYYYYYYYYYYYYYYYYYYYy");
		}


//		if(FML.isModLoaded("fihgu's Common Mod"))
//		{
//			if (FlyCommand.mustFlying(e.player)) {
//				e.player.capabilities.allowFlying = true;
//				e.player.sendPlayerAbilities();
//				System.out.println ("222eeee22 FLYYYYYYYYYYYYYYYYYYYy");
//			}
//		}
	}
}
