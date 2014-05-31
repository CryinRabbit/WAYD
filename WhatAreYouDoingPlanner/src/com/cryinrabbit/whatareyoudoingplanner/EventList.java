package com.cryinrabbit.whatareyoudoingplanner;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

/*
 * Centralized data stash that stores Event Objects
 */

public class EventList {
	
	private static EventList sEventList;
	private Context mAppContext;
	private ArrayList<Event> mEvents;
	
	private EventList(Context appContext) {
		mAppContext = appContext;
		mEvents = new ArrayList<Event>();
		
	}
	
	public ArrayList<Event> getEvents() {
		return mEvents;
	}
	
	
	public Event getEvent(UUID id) {
		for(Event e : mEvents) {
			if(e.getId().equals(id))
				return e;
		}
		return null;
	}
	
	public void removeEvent(Event e ) {
		mEvents.remove(e);
	}
	
	public void addEvent(Event e) {
		mEvents.add(e);
	}
	
	
	public static EventList get(Context c) {
		if(sEventList == null) {
			sEventList = new EventList(c.getApplicationContext());
		}
		
		return sEventList;
	}

}
