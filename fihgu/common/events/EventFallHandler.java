package fihgu.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import fihgu.common.commands.FlyCommand;
import fihgu.core.functions.Language;
import fihgu.core.functions.McColor;

public class EventFallHandler 
{
	
	@ForgeSubscribe
	public void onPlayerFall (LivingEvent e) {
		if(e.entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP)e.entity;
			if (!player.capabilities.allowFlying && FlyCommand.mustFlying(player)) {
				player.capabilities.allowFlying = true;
				player.sendPlayerAbilities();
				player.sendChatToPlayer(McColor.green + Language.translate("Le vol est activé"));
			}
		}
	}
}
