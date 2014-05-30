package com.cryinrabbit.service;

import com.cryinrabbit.domain.Event;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EventService {
	
	private DBOpenHelper dbOpenHelper;
	public EventService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	
	public void saveEvent(Event event){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into events(id,title,s_date,e_date,weekday,s_time,e_time,location) values(?,?,?,?,?,?,?,?)",
				new Object[]{event.getId(), event.getTitel(), event.getS_date(), event.getE_date(), event.getWeekday(), event.getS_time(), event.getE_time(), event.getLocation()});
		 
	}
	
	public void deleteEvent(int id){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from events where id=?", new Object[]{id});
	}
	
	public void updateEvent(Event event){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("update events set title=?, s_date=?,e_date=?, weekday=?, s_time=?, e_time=?, location=? where id=?", new Object[]{event.getTitel(), event.getE_date(), event.getS_date(), event.getWeekday(),event.getS_time(),event.getE_time(),event.getLocation(),event.getId()});
	}
	
	public Event findEvent(int id){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();

		Cursor cursor1=db.rawQuery("select * from events where id=?", new String[]{id+""});
		if(cursor1.moveToFirst()){
			String userid=cursor1.getString(cursor1.getColumnIndex("id"));
			String title=cursor1.getString(cursor1.getColumnIndex("title"));
			String s_date=cursor1.getString(cursor1.getColumnIndex("s_date"));
			String e_date=cursor1.getString(cursor1.getColumnIndex("e_date"));
			String weekday=cursor1.getString(cursor1.getColumnIndex("weekday"));
			String s_time=cursor1.getString(cursor1.getColumnIndex("s_time"));
			String e_time=cursor1.getString(cursor1.getColumnIndex("e_time"));
			String location=cursor1.getString(cursor1.getColumnIndex("location"));
			return new Event(Integer.parseInt(userid), title, s_date, e_date, weekday,s_time, e_time, location);
		}
		cursor1.close();
		return null;
	}
	
	public long getCountEvent(){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count (*) from events", null);
		cursor.moveToFirst();
		long result=cursor.getLong(0);
		return result;
	}
}