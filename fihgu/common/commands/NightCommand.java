package fihgu.common.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import fihgu.core.elements.CommandBase;
import fihgu.core.elements.Player;
import fihgu.core.elements.Request;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
public class NightCommand extends CommandBase
{
	
	public NightCommand()
	{
		name = "night";
		opOnly = true;
		usage = Language.translate("Change for nigth");
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.sendChatToPlayer("You have active the night");
		player.worldObj.setWorldTime(13500);
	}
}