package fihgu.protection;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import fihgu.core.shortcut.Forge;
import fihgu.teleport.death.EventHandler;

@Mod(modid="fihgu's Protection Mod", name="fihgu's Protection Mod", version="3.0.0")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class Mod_Protection
{
	@Instance("fihgu's Protection Mod")
	public static Mod_Protection instance;
	
	@SidedProxy(clientSide="fihgu.protection.ClientProxy", serverSide="fihgu.protection.ServerProxy")
	public static CommonProxy proxy;
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.init();
		Forge.registerEventHandler(new EventHandler());
	}
	
	@ServerStopping
	public void onServerStopping(FMLServerStoppingEvent e)
	{
		proxy.exit();
	}
}