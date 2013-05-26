package fihgu.teleport.death;

import fihgu.core.functions.Teleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;


public class EventHandler
{
	@ForgeSubscribe
	public void onLivingDeath (LivingDeathEvent event) {
		if(event.entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP)event.entity;
			Teleport.addLocationHistory (player);
		}
	}
}
