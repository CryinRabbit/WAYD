package com.cryinrabbit.whatareyoudoingplanner;

/*
 * This class handles the dialog for getting event info when
 * user taps on a cell of the list view in the main menu
 */

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
