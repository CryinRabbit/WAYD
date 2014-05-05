package com.cryinrabbit.whatareyoudoingplanner;

import android.app.Fragment;

public class EventActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new EventFragment();
	}
	
	

}
