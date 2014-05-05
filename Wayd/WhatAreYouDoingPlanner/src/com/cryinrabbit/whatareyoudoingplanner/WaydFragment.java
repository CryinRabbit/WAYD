package com.cryinrabbit.whatareyoudoingplanner;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TableLayout;

public class WaydFragment extends Fragment {
	
	private Button mAddEvent; //Will bring new activity to Add Event
	private Button mImageToText; //activate camera to take picture of schedule and convert to text
	private TableLayout mEventTable; //will hold list of events for selected day
	private CalendarView mCalendar; //displayed weekly calendar by default, user tap on day and change table to display task of that day

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_wayd, parent, false);
		
		//Set up calendar
		mCalendar = (CalendarView)v.findViewById(R.id.waydCalendar);
		
		//Setup table
		mEventTable = (TableLayout)v.findViewById(R.id.waydTaskTable);
				
		
		return v;
	}
	
	
	
	
}
