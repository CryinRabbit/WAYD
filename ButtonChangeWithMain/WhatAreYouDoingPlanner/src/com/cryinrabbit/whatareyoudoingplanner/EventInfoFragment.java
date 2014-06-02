package com.cryinrabbit.whatareyoudoingplanner;

/*
 * This class handles the dialog for getting event info when
 * user taps on a cell of the list view in the main menu
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;


@SuppressLint("ValidFragment")
public class EventInfoFragment extends DialogFragment {
	
	private String mTitle;
	@SuppressLint("ValidFragment")
	private String mLocation;
	private String mDescription;
	private String mTime;
	
	public EventInfoFragment(String s, String location, String description, String time) {
		mTitle = s;
		mLocation = location;
		mDescription = description;
		mTime = time;
 		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity()).setTitle(mTitle)
				.setMessage("Where: " + mLocation + "\n" + "When:  " + mTime + "\n" + "Description: " + mDescription)
				.setPositiveButton(android.R.string.ok, null).create();
	}

}
