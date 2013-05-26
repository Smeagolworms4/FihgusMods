package fihgu.common.commands;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import fihgu.core.elements.CommandBase;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
import fihgu.core.functions.PlayerManager;
import fihgu.core.io.SaveFile;
import fihgu.teleport.elements.WarpPoint;

public class FlyCommand extends CommandBase
{
	
	private static SaveFile _flyingSaveFile = new SaveFile("fly.txt","./fihgu/common/");
	private static ArrayList<String> _usersFlying = new ArrayList<String>();
	
	public static void loadConfig () {
		_flyingSaveFile.load();
		_usersFlying.clear();
		
		for(String info : _flyingSaveFile.data) {
			_usersFlying.add(info);
		}
	}
	
	public static void saveConfig () {
		_flyingSaveFile.clear ();
		for(String username : _usersFlying)
			_flyingSaveFile.data.add(username);
		_flyingSaveFile.save(false);
	}
	
	public static boolean mustFlying (EntityPlayerMP player) {
		return _usersFlying.contains(player.username);
	}
	
	public FlyCommand()
	{
		name = "fly";
		opOnly = true;
		usage = Language.translate(" [<PlayerName>]: You toggle your fly capability or for other player's fly capability");
	}
	
	public void toggleFlying (EntityPlayerMP player) {
		
		if (player.capabilities.allowFlying) {
			player.capabilities.allowFlying = false;
			player.sendPlayerAbilities();
			
			while (_usersFlying.contains (player.username)) {
				_usersFlying.remove(player.username);
			}
			saveConfig ();
			
			player.sendChatToPlayer(McColor.green + Language.translate("Le vol est désactivé"));
		} else {
			player.capabilities.allowFlying = true;
			player.sendPlayerAbilities();
			_usersFlying.add (player.username);
			saveConfig ();
			player.sendChatToPlayer(McColor.green + Language.translate("Le vol est activé"));
		}
	}
	
	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		if (args.length == 0)
		{
			toggleFlying (player);
		} else
		{
			EntityPlayerMP target = PlayerManager.getPlayer(args[0], true);

			if (target == null) {
				target = PlayerManager.getPossiblePlayer(args[0]);
			}
			
			if (target != null) {
				if (target.capabilities.allowFlying) {
					toggleFlying (target);
					player.sendChatToPlayer(McColor.green + Language.translate("Le vol est désactivé pour "+args[0]));
				} else {
					toggleFlying (target);
					target.sendChatToPlayer(McColor.green + Language.translate("Le vol est activé"));
				}
			} else {
				player.sendChatToPlayer(McColor.red + Language.translate("Le joueur ") + McColor.green + args[0] + McColor.red + Language.translate(" est introuvable."));
			}
		}
		
	}
}