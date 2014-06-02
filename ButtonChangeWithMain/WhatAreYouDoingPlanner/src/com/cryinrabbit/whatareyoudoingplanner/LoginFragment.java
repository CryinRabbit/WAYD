package com.cryinrabbit.whatareyoudoingplanner;

/*
 * Handles the login screen of the app
 * Facebook stuff is handled here
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment {
	
	private Button mLoginButton;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.login_fragment, parent, false);
		
		mLoginButton = (Button)v.findViewById(R.id.login_button);
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), WaydActivity.class);
				startActivity(i);
			}
		});
		
		return v;
	}			 
	
}
