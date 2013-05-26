package fihgu.common;

import fihgu.common.commands.*;

public class CommonProxy 
{
	public void init() 
	{
		new FlyCommand().register();
		new SunCommand().register();
		new RainCommand().register();
		new DayCommand().register();
		new NightCommand().register();
	}
	
}
