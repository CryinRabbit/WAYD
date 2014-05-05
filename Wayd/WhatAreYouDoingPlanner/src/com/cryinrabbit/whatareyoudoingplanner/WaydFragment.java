package com.cryinrabbit.whatareyoudoingplanner;

import java.util.ArrayList;
import java.util.Map;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WaydFragment extends ListFragment {
	
	private ArrayList<Event> mEvents;
	private static final String EVENT_INFO_DIALOG ="info";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		mEvents = EventList.get(getActivity()).getEvents();
		
		
	}
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.wayd, menu);		
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.menu_item_new_event:
				Event e = new Event();
				EventList.get(getActivity()).addEvent(e);
				Intent i = new Intent(getActivity(), EventPagerActivity.class);
				
				
				
		}
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_wayd, parent, false);
		ListView lv = (ListView)v.findViewById(R.id.listView1);
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getActivity(), android.R.layout.simple_list_item_1, mEvents);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				FragmentManager fm = getActivity().getFragmentManager();
				TextView clickedView = (TextView) view;
				
				
				EventInfoFragment dialog = new EventInfoFragment((String) clickedView.getText());
				dialog.show(fm, EVENT_INFO_DIALOG);
				
				
			}
		});
		
		
		
		
		return v;
	}
	
	
	
	
}
