package com.critmassgames.discordthrottling;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainStartup {
	
	public static void main(String[] args) {
		startBatchProcesses();
		setupDiscordBot();
	}

	public static void startBatchProcesses() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		
		DiscordThrottler throttler = new DiscordThrottler();
		
		// run this 1 second from now, and every 1 second after that.
		executor.scheduleAtFixedRate(throttler,1,1,TimeUnit.SECONDS);
	}
	
	public static void setupDiscordBot() {
		//bot setup code goes here
		//...

		//simulate the need for throttling with a for loop
		for (int i=0;i<30;i++) {
			//when you want to make an API call, wrap it in an anonymous runnable like so:
			//if (user.shouldBeBanned()) {
				DiscordThrottler.enqueue(()->{
					FakeDiscordClient.doDiscordAction("Ban Verdant");
				});
			//}
		}
	}
}
