package fihgu.common.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.storage.WorldInfo;
import fihgu.core.elements.CommandBase;
import fihgu.core.elements.Location;
import fihgu.core.elements.Player;
import fihgu.core.elements.Request;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;

public class SunCommand extends CommandBase
{
	public SunCommand()
	{
		name = "sun";
		opOnly = true;
		usage = Language.translate("Active the sun");
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.sendChatToPlayer("You have active the sun");
		WorldInfo info = player.worldObj.getWorldInfo ();
		info.setRaining(false);
		info.setThundering(false);
		player.worldObj.updateWeatherBody ();
	}
}
