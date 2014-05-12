package com.cryinrabbit.whatareyoudoingplanner;

/*
 * This fragment takes care of the Add Event section
 * After user tap on + icon on Main menu, it is directed to this activity to add event
 * User can input title, time and location for the event
 */


import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EventFragment extends android.support.v4.app.Fragment {
	
	private Event mEvent; //holds event object
	private EditText mTitleField; //title field where user enters name of event
	private Button mTimeButton; //button where user selects the time
	private EditText mLocationField; //field where user enters location
	public static final String EXTRA_EVENT_ID =
			"com.cryinrabbit.whatareyoudoingplanner.event_id";
	private static final String DIALOG_TIME = "time";
	private static final int REQUEST_TIME = 0;
	private Button mDateButton;
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID eventId = (UUID)getArguments().getSerializable(EXTRA_EVENT_ID);
		
		mEvent = EventList.get(getActivity()).getEvent(eventId);
	
		setHasOptionsMenu(true);
	}
	
	
	

	//returns fragment to pager activity
	public static EventFragment newInstance(UUID eventId) {
		
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_EVENT_ID, eventId);
		
		EventFragment fragment = new EventFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	//updates the time in the time button
	private void updateTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mEvent.getTime());
		
		
		mTimeButton.setText(timeFormat(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE)));
		
	}
	
	//updates the date in the date button
	private void updateDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mEvent.getStartDate());
		mDateButton.setText(dateFormat(cal.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR)));
		
	}
	
	//converts to desired time format
	private String timeFormat(int hour, int minute) {
		if(minute < 10)
			return hour + ":" + "0" + minute;
		else
			return hour + ":" + minute;
		}
	
	//convert to desired date formar
	private String dateFormat(int dayWeek, int month, int dayNumber, int year) {
		String dayOfWeek = "", monthName = "";
		switch(dayWeek) {
			case 1:
				dayOfWeek = "Sunday";
				break;
			case 2:
				dayOfWeek = "Monday";
				break;
			case 3:
				dayOfWeek = "Tuesday";
				break;
			case 4:
				dayOfWeek = "Wednesday";
				break;
			case 5:
				dayOfWeek = "Thursday";
				break;
			case 6:
				dayOfWeek = "Friday";
				break;
			case 7:
				dayOfWeek = "Saturday";
				break;
		}
		
		switch(month) {
			case 0:
				monthName = "January";
				break;
			case 1:
				monthName = "February";
				break;
			case 2:
				monthName = "March";
				break;
			case 3:
				monthName = "April";
				break;
			case 4:
				monthName = "May";
				break;
			case 5:
				monthName = "June";
				break;
			case 6:
				monthName = "July";
				break;
			case 7:
				monthName = "August";
				break;
			case 8:
				monthName = "September";
				break;
			case 9:
				monthName = "October";
				break;
			case 10:
				monthName = "November";
				break;
			case 11:
				monthName = "December";
				break;
		}
		
		return dayOfWeek + " "+ monthName + " " + dayNumber + " " + year;
	}
	
	
	//handles the action whether user chose to set date or chose to set time
	//updates the event object with selected times and date
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK)
			return;
		if(requestCode == REQUEST_TIME) {
			Date time = (Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
			mEvent.setTime(time);
			updateTime();
		}
		
		else if(requestCode == REQUEST_DATE) {
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mEvent.setStartDate(date);
			mDateButton.setText(mEvent.getStartDate().toString());
			updateDate();
		}
	}
	
	/*displays the add event view with title field, location field, time button and date button
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.eventfragment, parent, false);

		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

		//takes care of title field
		mTitleField = (EditText)v.findViewById(R.id.event_title);
		mTitleField.setText(mEvent.getTitle());
		getActivity().setTitle(mEvent.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mEvent.setTitle(c.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
	
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				
			}
			
			
				
		});
		
		//takes care of location field
		mLocationField = (EditText)v.findViewById(R.id.event_location);
		mLocationField.setText(mEvent.getLocation());
		getActivity().setTitle(mEvent.getLocation());
		mLocationField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mEvent.setLocation(c.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
	
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				
			}
			
			
				
		});

		
		//Take care of time button action
		mTimeButton = (Button)v.findViewById(R.id.event_time);
		mTimeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment.newInstance(mEvent.getTime());
				dialog.setTargetFragment(EventFragment.this, REQUEST_TIME);
				dialog.show(fm, DIALOG_TIME);
				
			}
		});
		
		//Take care of date button action
		mDateButton = (Button)v.findViewById(R.id.event_date);
		mDateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mEvent.getStartDate());
				dialog.setTargetFragment(EventFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		
		
		return v;
	}
	
}
