package com.cryinrabbit.whatareyoudoingplanner;

/*
 * Class to handle action of swiping to right and left through
 * events created by the user
 */

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;




public class EventPagerActivity extends FragmentActivity {
	
	private ViewPager mViewPager;
	private ArrayList<Event> mEvents;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e("Msg", "EventPagerActivity executed");
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		mEvents = EventList.get(this).getEvents();
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return mEvents.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				Event e = mEvents.get(pos);
				return EventFragment.newInstance(e.getId());
			}
		});
		
		UUID eventId = (UUID)getIntent().getSerializableExtra(EventFragment.EXTRA_EVENT_ID);
		for(int i = 0; i < mEvents.size(); i++) {
			if(mEvents.get(i).getId().equals(eventId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
		
		
		
		
		
	}

}
