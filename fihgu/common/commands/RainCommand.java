package fihgu.common.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.storage.WorldInfo;
import fihgu.core.elements.CommandBase;
import fihgu.core.elements.Location;
import fihgu.core.elements.Player;
import fihgu.core.elements.Request;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
import fihgu.core.functions.PlayerManager;

public class RainCommand extends CommandBase
{
	private EntityPlayerMP sender;

	public RainCommand()
	{
		name = "rain";
		opOnly = true;
		usage = Language.translate("Active the rain");
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.sendChatToPlayer("Vous avez programmé la pluie");
		WorldInfo info = player.worldObj.getWorldInfo ();
		info.setRaining(true);
		info.setThundering(false);
		player.worldObj.updateWeatherBody ();
	}
}