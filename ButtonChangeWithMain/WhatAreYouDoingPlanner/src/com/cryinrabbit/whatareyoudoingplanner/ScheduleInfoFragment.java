package com.cryinrabbit.whatareyoudoingplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ScheduleInfoFragment extends DialogFragment {
	
	private String mScheduleTitle;
	private String mScheduleText;
	
	public ScheduleInfoFragment(String title, String text) {
		mScheduleTitle = title;
		mScheduleText = text;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity()).setTitle(mScheduleTitle).setMessage(mScheduleText)
				.setPositiveButton(android.R.string.ok, null).create();
	}
	

}
