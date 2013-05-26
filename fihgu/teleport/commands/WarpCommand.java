package fihgu.teleport.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import fihgu.core.elements.CommandBase;
import fihgu.core.elements.Location;
import fihgu.core.elements.Player;
import fihgu.core.elements.Request;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
import fihgu.core.functions.PlayerManager;
import fihgu.core.functions.Teleport;
import fihgu.teleport.elements.WarpPoint;

public class WarpCommand extends CommandBase
{
	public WarpCommand()
	{
		name = "warp";
		usage = Language.translate(" [<PlayerName>] <WarpPointName>: Warp you or a player to a location.");
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		if (args.length == 0)
		{
			String result = Language.translate(" Warp list : ");
			for (WarpPoint warp : WarpPoint.warpPoints) {
				result += warp.name+" ";
			}
			player.sendChatToPlayer(result);
		} 
		else if (args.length == 1)
		{
			if (WarpPoint.getWarpPoint(args[0]) != null) {
				Teleport.warp(player, WarpPoint.getWarpPoint(args[0]).location, false);
				player.sendChatToPlayer(McColor.green + Language.translate("Warped to ") + args[0] + ".");
			} else {
				player.sendChatToPlayer(McColor.darkRed	+ Language.translate("Warp Point ") + McColor.aqua + args[0] + McColor.darkAqua + Language.translate(" does not exist!"));
			}
		} else {
			if (WarpPoint.getWarpPoint(args[1]) != null) {
				
				final EntityPlayerMP target = PlayerManager.getPlayer(args[0], true);
				final EntityPlayerMP sender = player;
				final String warpdest = args[1];
				
				if (target != null)
				{
					new Request(new Player(target), 30) 
					{
						EntityPlayerMP from = sender;
						EntityPlayerMP to = target;
						String dest = warpdest;
						@Override
						public void accept()
						{
							Teleport.warp(to, WarpPoint.getWarpPoint(dest).location, false);
							to.sendChatToPlayer(McColor.green + Language.translate("Warped to ") + dest + " by " + McColor.darkRed + from.username);
							
							from.sendChatToPlayer(McColor.aqua + to.username + McColor.green + Language.translate(" has accepted your warp request."));
							from.sendChatToPlayer(McColor.aqua + to.username + McColor.green + Language.translate(" is warpped to ") + McColor.aqua + to.username);
						}
					};
				} else {
					final EntityPlayerMP target2 = PlayerManager.getPossiblePlayer (args[0]);
					if (target2 != null)
					{
						new Request(new Player(target2), 30) 
						{
							EntityPlayerMP from = sender;
							EntityPlayerMP to = target2;
							String dest = warpdest;
							@Override
							public void accept()
							{
								Teleport.warp(to, WarpPoint.getWarpPoint(dest).location, false);
								to.sendChatToPlayer(McColor.green + Language.translate("Warped to ") + dest + " by " + McColor.darkRed + from.username);
								
								from.sendChatToPlayer(McColor.aqua + to.username + McColor.green + Language.translate(" has accepted your warp request."));
								from.sendChatToPlayer(McColor.aqua + to.username + McColor.green + Language.translate(" is warpped to ") + McColor.aqua + to.username);
							}
						};
					} else {
						player.sendChatToPlayer(McColor.red + Language.translate("Le joueur ") + McColor.green + args[0] + McColor.red + Language.translate(" est introuvable."));
					}
				}
				
			} else {
				player.sendChatToPlayer(McColor.darkRed	+ Language.translate("Warp Point ") + McColor.aqua + args[1] + McColor.darkAqua + Language.translate(" does not exist!"));
			}
		}
	}
}
