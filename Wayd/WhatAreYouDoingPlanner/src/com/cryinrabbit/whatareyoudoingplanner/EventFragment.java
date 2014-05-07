package com.cryinrabbit.whatareyoudoingplanner;


import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EventFragment extends android.support.v4.app.Fragment {
	
	private Event mEvent;
	private EditText mTitleField;
	private Button mTimeButton;
	public static final String EXTRA_EVENT_ID =
			"com.cryinrabbit.whatareyoudoingplanner.event_id";
	private static final String DIALOG_TIME = "time";
	private static final int REQUEST_TIME = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID eventId = (UUID)getArguments().getSerializable(EXTRA_EVENT_ID);
		
		mEvent = EventList.get(getActivity()).getEvent(eventId);
		
		setHasOptionsMenu(true);
	}
	

	
	public static EventFragment newInstance(UUID eventId) {
		
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_EVENT_ID, eventId);
		
		EventFragment fragment = new EventFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	private void updateTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mEvent.getTime());
		
		
		mTimeButton.setText(timeFormat(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE)));
		
	}
	
	private String timeFormat(int hour, int minute) {
		if(minute < 10)
			return hour + ":" + "0" + minute;
		else
			return hour + ":" + minute;
		}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK)
			return;
		if(requestCode == REQUEST_TIME) {
			Date time = (Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
			mEvent.setTime(time);
			updateTime();
		}
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.eventfragment, parent, false);

		getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);

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
				
				if(mEvent.getTitle().equals("")) {
					mEvent.setTitle("Untitled");
				}
				
			}
			
			
				
		});
		
		
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
		
		return v;
	}
	
}
