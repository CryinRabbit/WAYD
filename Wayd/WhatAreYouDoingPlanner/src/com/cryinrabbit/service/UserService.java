package com.cryinrabbit.service;

import java.util.ArrayList;
import java.util.List;
import com.cryinrabbit.domain.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
	private DBOpenHelper dbOpenHelper;
	public UserService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	public void saveUser(User user){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into users(usersid,name) values(?,?)",
			new Object[]{user.getId(),user.getName()});
	}
	
	public void deleteUser(String id){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from users where usersid=?", new Object[]{id});
	}
	
	public void updateUser(User user){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("update users set name=? where usersid=?", new Object[]{user.getName(),user.getId()});
	}

	
	
	public User findUser(String id){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from users where usersid=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			String usersid=cursor.getString(cursor.getColumnIndex("usersid"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			return new User(usersid,name);
		}
		cursor.close();
		return null;
	}
	
	public List<User> getScrollDataUser(int offset,int maxResult){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		List<User> user=new ArrayList<User>();
		Cursor cursor=db.rawQuery("select * from users limit ?,?", 
				new String[]{String.valueOf(offset),String.valueOf(maxResult)});
		while(cursor.moveToNext()){
			String usersid=cursor.getString(cursor.getColumnIndex("usersid"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			user.add(new User(usersid,name));
		}
		cursor.close();
		return user;
	}
	
	public long getCountUser(){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count (*) from users", null);
		cursor.moveToFirst();
		long result=cursor.getLong(0);
		return result;
	}

}
