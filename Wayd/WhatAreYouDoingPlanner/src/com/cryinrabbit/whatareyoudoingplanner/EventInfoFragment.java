package com.cryinrabbit.whatareyoudoingplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class EventInfoFragment extends DialogFragment {
	
	private String mTitle;
	
	public EventInfoFragment(String s) {
		mTitle = s;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity()).setTitle(mTitle).setMessage("No Location Yet")
				.setPositiveButton(android.R.string.ok, null).create();
	}

}
