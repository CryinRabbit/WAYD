package com.cryinrabbit.whatareyoudoingplanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class WaydJSONSerializer {
	
	private Context mContext;
	private String mFilename;
	
	public WaydJSONSerializer(Context c, String f) {
		mContext = c;
		mFilename = f;
	}
	
	public ArrayList<Event> loadEvents() throws IOException, JSONException {
		
		ArrayList<Event> events = new ArrayList<Event>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for(int i = 0; i < array.length(); i++) {
				events.add(new Event(array.getJSONObject(i)));
			}
			
		} catch(FileNotFoundException e) {
			Log.d("msg", "could not load events");
		} finally {
			if(reader != null)
				reader.close();
		}
		
		return events;
	}
	
	public void saveEvents(ArrayList<Event> events) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for(Event e : events) 
			array.put(e.toJSON());
			
		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if(writer != null)
				writer.close();
		}
	}

}
