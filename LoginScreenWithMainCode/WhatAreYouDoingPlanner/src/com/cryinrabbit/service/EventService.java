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
		db.execSQL("insert into events(usersid,title,date,s_time,e_time,location) values(?,?,?,?,?,?)",
				new Object[]{event.getId(), event.getTitel(), event.getDate(), event.getS_time(), event.getE_time(), event.getLocation()});
		
	}
	
	public void deleteEvent(String id){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from events where usersid=?", new Object[]{id});
	}
	
	public void updateEvent(Event event){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("update events set title=?, date=?, s_time=?, e_time=?, location=? where usersid=?", new Object[]{event.getTitel(),event.getDate(),event.getS_time(),event.getE_time(),event.getLocation(),event.getId()});
	}
	
	public Event findEvent(String id){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();

		Cursor cursor1=db.rawQuery("select * from events where usersid=?", new String[]{id.toString()});
		if(cursor1.moveToFirst()){
			String usersid=cursor1.getString(cursor1.getColumnIndex("usersid"));
			String title=cursor1.getString(cursor1.getColumnIndex("title"));
			String date=cursor1.getString(cursor1.getColumnIndex("date"));
			String s_time=cursor1.getString(cursor1.getColumnIndex("s_time"));
			String e_time=cursor1.getString(cursor1.getColumnIndex("e_time"));
			String location=cursor1.getString(cursor1.getColumnIndex("location"));
			return new Event(usersid,title,date,s_time,e_time,location);
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