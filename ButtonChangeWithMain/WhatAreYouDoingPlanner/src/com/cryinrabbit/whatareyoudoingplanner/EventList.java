package com.cryinrabbit.whatareyoudoingplanner;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

/*
 * Centralized data stash that stores Event Objects
 */

public class EventList {
	
	//Test code for JSON, delete or comment if not working!!!
	private static final String TAG = "EventList";
	private static final String FILENAME= "events.json";
	
	
	private static EventList sEventList;
	private Context mAppContext;
	private ArrayList<Event> mEvents;
	
	//Test code for JSON, delete or comment if not working!!!
	private WaydJSONSerializer mSerializer;
	
	private EventList(Context appContext) {
		mAppContext = appContext;
		//mEvents = new ArrayList<Event>();
		mSerializer = new WaydJSONSerializer(mAppContext, FILENAME);
		
		//TEST CODE FOR JSON, DELETE IF NOT WORKING!!
		try {
			mEvents = mSerializer.loadCrimes();
		} catch(Exception e) {
			mEvents = new ArrayList<Event>();
			Log.e(TAG, "Error loading events: ", e);
		}
		
	}
	
	/*
	 * TEST CODE FOR JSON
	 * DELETE IF NOT WORKING
	 */
	public boolean saveEvents() {
		try {
			mSerializer.saveEvents(mEvents);
			Log.d(TAG, "events saved to file");
			return true;
		} catch(Exception e ){
			Log.e(TAG, "Error saving event: ", e);
			return false;
		}
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
