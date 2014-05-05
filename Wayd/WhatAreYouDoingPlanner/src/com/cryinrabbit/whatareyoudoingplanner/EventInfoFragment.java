package com.cryinrabbit.whatareyoudoingplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class EventInfoFragment extends DialogFragment {
	
	private String mTitle;
	
	public EventInfoFragment(String s) {
		mTitle = s;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity()).setTitle(mTitle)
				.setPositiveButton(android.R.string.ok, null).create();
	}

}
