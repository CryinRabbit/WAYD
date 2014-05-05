package com.cryinrabbit.whatareyoudoingplanner;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class EventRoom {
	
	private static EventRoom sEventRoom;
	private Context mAppContext;
	private ArrayList<Event> mEvents;
	
	
	private EventRoom(Context appContext) {
		mAppContext = appContext;
		mEvents = new ArrayList<Event>();
	}
	
	public ArrayList<Event> getEvents() {
		return mEvents;
	}
	
	public Event getEvent(UUID id) {
		for(Event e : mEvents)  {
			if(e.getId().equals(id))
				return e;
		}
		
		return null;
	}
	
	public static EventRoom get(Context c) {
		if(sEventRoom == null) {
			sEventRoom = new EventRoom(c.getApplicationContext());
		}
		
		return sEventRoom;
	}

}
