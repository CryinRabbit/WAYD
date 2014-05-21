package com.cryinrabbit.whatareyoudoingplanner;

import java.util.UUID;

public class MySchedule {
	
	private String schedule;
	private UUID mId;
	
	public MySchedule(String s) {
		mId = UUID.randomUUID();
		s = schedule;
	}
	
	public MySchedule() {
		mId = UUID.randomUUID();
		schedule = "No schedule yet";
	}
	
	public void setSchedule(String s) {
		schedule = s;
	}
	
	public String getSchedule() {
		return schedule;
	}
	
	public UUID getId() {
		return mId;
	}

}
