package fihgu.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import fihgu.common.commands.FlyCommand;
import fihgu.common.events.EventFallHandler;
import fihgu.core.shortcut.Forge;

@Mod(modid="fihgu's Common Mod", name="fihgu's Common Mod", version="3.0.0")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class Mod_Common 
{
	@Instance("fihgu's Common Mod")
	public static Mod_Common instance;
	
	@SidedProxy(clientSide="fihgu.common.ClientProxy", serverSide="fihgu.common.ServerProxy")
	public static CommonProxy proxy;
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.init();
		Forge.registerEventHandler(new EventFallHandler());
		FlyCommand.loadConfig ();
	}
	
	@ServerStopping
	public void onServerStopping(FMLServerStoppingEvent e)
	{
		FlyCommand.saveConfig ();
	}
}