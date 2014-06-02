package com.cryinrabbit.whatareyoudoingplanner;

/*
 * This class handles all the model for the event
 */

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;



public class Event {
	
	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_DATE = "date";
	private static final String JSON_TIME = "time";
	private static final String JSON_LOCATION = "location";
	private static final String JSON_DESCRIPTION = "description";
	
	private String mTitle;
	private UUID mId;
	private Date mStartDate;
	private Date mEndDate;
	private String mLocation;
	private String mDescription;
	private Date mTime;
	
	/*
	 * Test code FOR JSON
	 * DELETE IF NOT WORKING
	 */
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE, mTitle);
		json.put(JSON_DATE, mStartDate.getTime());
		json.put(JSON_TIME, mTime.getTime());
		json.put(JSON_LOCATION, mLocation);
		json.put(JSON_DESCRIPTION, mDescription);
		
		return json;
	}
	
	/*
	 * Test code FOR JSON
	 * DELETE IF NOT WORKING
	 */
	
	public Event(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		if(json.has(JSON_TITLE)) {
			mTitle = json.getString(JSON_TITLE);
		}
		
		mTime = new Date(json.getLong(JSON_TIME));
		mLocation = json.getString(JSON_LOCATION);
		mDescription = json.getString(JSON_DESCRIPTION);
		mStartDate = new Date(json.getLong(JSON_DATE));
	}
 	
	
	
	public Event() {
		mId = UUID.randomUUID();
		mStartDate = new Date();
		mEndDate = new Date();
		mTime = new Date();
		mLocation = "";
		mDescription = "";
		mTitle = "";
		
	}
	
	public Event clone() {
		Event e = new Event();
		
		e.mStartDate = this.mStartDate;
		e.mTitle = this.mTitle;
		e.mId = this.mId;
		e.mTime = this.mTime;
		e.mLocation = this.mLocation;
		e.mDescription = this.mDescription;
		
		return e;
	}
	

	public void setLocation(String loc) {
		mLocation = loc;
	}
	
	public void setDescription(String des) {
		mDescription = des;
		
	}
	
	public String getLocation() {
		return mLocation;
	}
	
	public String getDescription() {
		return mDescription;
	}
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public Date getStartDate() {
		return mStartDate;
	}

	public void setStartDate(Date startDate) {
		mStartDate = startDate;
	}

	public Date getEndDate() {
		return mEndDate;
	}
	
	public void setTime(Date time) {
		mTime = time;
	}
	
	public Date getTime() {
		return mTime;
	}

	public void setEndDate(Date endDate) {
		mEndDate = endDate;
	}

	public void setId(UUID id) {
		mId = id;
	}

	public UUID getId() {
		return mId;
	}
	
	public String toString() {
		return mTitle;
	}


}
