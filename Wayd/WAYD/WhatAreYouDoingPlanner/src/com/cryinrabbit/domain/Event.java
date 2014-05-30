package com.cryinrabbit.domain;

public class Event {
	private int id;
	private String titel;
	private String s_date;
	private String e_date;
	private String weekday;
	private String s_time;
	private String e_time;
	private String location;
	
	

	

	public Event(int id, String titel, String s_date, String e_date, String weekday,
			String s_time, String e_time, String location) {
		super();
		this.id = id;
		this.titel = titel;
		this.s_date = s_date;
		this.e_date = e_date;
		this.weekday=weekday;
		this.s_time = s_time;
		this.e_time = e_time;
		this.location = location;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getTitel() {
		return titel;
	}





	public void setTitel(String titel) {
		this.titel = titel;
	}





	public String getS_date() {
		return s_date;
	}





	public void setS_date(String s_date) {
		this.s_date = s_date;
	}





	public String getE_date() {
		return e_date;
	}





	public void setE_date(String e_date) {
		this.e_date = e_date;
	}





	public String getWeekday() {
		return weekday;
	}





	public void setWeekday(String weekday) {
		this.weekday = weekday;
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





	@Override
	public String toString() {
		return "Event [id=" + id + ", titel=" + titel + ", s_date=" + s_date
				+ ", e_date=" + e_date + ", weekday=" + weekday + ", s_time="
				+ s_time + ", e_time=" + e_time + ", location=" + location
				+ "]";
	}


	


		
}
