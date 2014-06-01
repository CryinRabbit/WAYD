package com.cryinrabbit.whatareyoudoingplanner;

/*
 * Class that handles the time button to display
 * a dialog so that user can select time for an event
 */

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerFragment extends DialogFragment {
	
	public static final String EXTRA_TIME =
			"com.cryinrabbit.whatareyoudoingplanner.time";
	
	private Date mTime;
	
	public static TimePickerFragment newInstance(Date time) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TIME, time);
		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	private void sendResult(int resultCode) {
		if(getTargetFragment() == null)
			return;
		
		Intent i = new Intent();
		i.putExtra(EXTRA_TIME, mTime);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		mTime = (Date)getArguments().getSerializable(EXTRA_TIME);
		
		//Create calendar
		Calendar calendar = Calendar.getInstance();
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time,null);
		TimePicker timePicker = (TimePicker)v.findViewById(R.id.dialog_time_timePicker);
		
		timePicker.setIs24HourView(true);
		
		
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
				cal.set(Calendar.MINUTE, minute);
				mTime = cal.getTime();
				
				getArguments().putSerializable(EXTRA_TIME, mTime);
				
			}
			
		});
		
		
		
		return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.time_picker_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);
						
					}
				}).create();		
	}
	

}
