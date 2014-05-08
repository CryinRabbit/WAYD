package com.cryinrabbit.whatareyoudoingplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class EventInfoFragment extends DialogFragment {
	
	private String mTitle;
	private String mLocation;
	
	public EventInfoFragment(String s, String location) {
		mTitle = s;
		mLocation = location;
		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity()).setTitle(mTitle).setMessage(mLocation)
				.setPositiveButton(android.R.string.ok, null).create();
	}

}
