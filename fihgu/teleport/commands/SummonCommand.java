package fihgu.teleport.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import fihgu.core.elements.CommandBase;
import fihgu.core.elements.Location;
import fihgu.core.elements.Player;
import fihgu.core.elements.Request;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
import fihgu.core.functions.PlayerManager;
import fihgu.core.functions.Warp;

public class SummonCommand extends CommandBase
{
	private EntityPlayerMP sender;

	public SummonCommand()
	{
		name = "summon";
		usage = Language.translate(" <Player name>: Summon a player to yourself.");
	}

	@Override
	public void processPlayer(EntityPlayerMP senderEntity, String[] args)
	{
		final Player sender = new Player(senderEntity);
		final Player target = new Player(PlayerManager.getPossiblePlayer(args[0]));
		
		if(args.length < 1 || args.length > 1)
		{
			this.argumentMismatch(sender.getEntity());
		} 
		else if (args.length == 1)
		{
			if (target != null)
			{
				sender.msg(McColor.green + Language.translate("Request sent to ") + target.name + "!");
				target.msg(McColor.green + sender.name	+ Language.translate(" has send you a Warp request!"));
				target.msg(McColor.green + Language.translate("Please accept with /y or deny with /n."));
				
				new Request(target, 30) 
				{
					Player from = sender;
					Player to = target;
					
					@Override
					public void accepted()
					{
						Warp.warpTo(to.getEntity(), from.getEntity());
					}
				};
				
			}
		}
	}
}