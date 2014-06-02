package com.cryinrabbit.whatareyoudoingplanner;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class EventActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID eventId = (UUID)getIntent().getSerializableExtra(EventFragment.EXTRA_EVENT_ID);
		
		return EventFragment.newInstance(eventId);
	}
	
	

}
