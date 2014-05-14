package com.cryinrabbit.domain;

public class Event {
	private String id;
	private String titel;
	private String date;
	private String s_time;
	private String e_time;
	private String location;
	
	public Event(String id, String titel, String date, String s_time,
			String e_time, String location) {
		super();
		this.id = id;
		this.titel = titel;
		this.date = date;
		this.s_time = s_time;
		this.e_time = e_time;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String e_time) {
		this.e_time = e_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String toString() {
		return "Event [id=" + id + ", titel=" + titel + ", date=" + date
				+ ", s_time=" + s_time + ", e_time=" + e_time + ", location="
				+ location + "]";
	}
		
}
