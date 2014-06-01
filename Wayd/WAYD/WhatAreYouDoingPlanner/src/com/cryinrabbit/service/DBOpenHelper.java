package com.cryinrabbit.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "cryinrabbit.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE users(usersid string primary key, name varchar(20))");
		db.execSQL("CREATE TABLE events(id integer primary key autoincrement, title varchar(20), s_date varchar(20), e_date varchar(20), weekday varchar(20),s_time varchar(20), e_time varchar(20), location varchar(20))");
	}


	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("ALERT TABLE user ADD phone VARCHAR(12) NULL");
		onCreate(db);
	}
 
}
