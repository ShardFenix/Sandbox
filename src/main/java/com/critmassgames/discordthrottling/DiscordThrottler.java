package com.critmassgames.discordthrottling;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DiscordThrottler extends TimerTask {
	
	private final static ConcurrentLinkedQueue<Runnable> apiCalls=new ConcurrentLinkedQueue<>();
	
	private final static int ALLOWED_CALLS_PER_SECOND=2;
	
	public static void enqueue(Runnable r) {
		System.out.println("Adding API call to queue.");
		apiCalls.add(r);
	}
	
	@Override
	public void run() {
		System.out.println("Running up to "+ALLOWED_CALLS_PER_SECOND+" api calls...");
		for (int i=0; i<ALLOWED_CALLS_PER_SECOND && !apiCalls.isEmpty();i++) {
			Runnable r = apiCalls.poll();
			//probably a redundant null check
			if (r!=null) {
				r.run();
			}
		}
	}

}
