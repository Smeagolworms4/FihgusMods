package fihgu.common.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import fihgu.core.elements.CommandBase;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;
import fihgu.core.functions.Teleport;

public class DayCommand extends CommandBase
{
	public DayCommand()
	{
		name = "day";
		opOnly = true;
		usage = Language.translate("Change for day");
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.sendChatToPlayer("Vous avez programmer le jour");
		player.worldObj.setWorldTime(0);
	}
}