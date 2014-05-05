package com.cryinrabbit.whatareyoudoingplanner;

import java.util.Date;
import java.util.UUID;

public class Event {
	
	private String mTitle;
	private UUID mId;
	private Date mStartDate;
	private Date mEndDate;
	private String mLocation;
	
	public Event() {
		mId = UUID.randomUUID();
		mStartDate = new Date();
		mEndDate = new Date();
		
	}
	
	
	public void setLocation(String loc) {
		mLocation = loc;
	}
	
	public String getLocation() {
		return mLocation;
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

	public void setEndDate(Date endDate) {
		mEndDate = endDate;
	}

	public void setId(UUID id) {
		mId = id;
	}

	public UUID getId() {
		return mId;
	}

}
