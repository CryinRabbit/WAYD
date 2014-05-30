package com.cryinrabbit.whatareyoudoingplanner;

/*
 *This is the main menu of the app
 *Main activity
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;
import android.widget.TextView;



public class WaydFragment extends ListFragment {
	
	private ArrayList<Event> mEvents;
	private static final String EVENT_INFO_DIALOG ="info";
	private static final String TAG = "EventListFragment";
	private ListView lv;
	private CalendarView calendar;
	private int yearSelected; //year selected by user
	private int monthSelected; //month selected by user
	private int dayOfMonthSelected; //day of month selected by user
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		mEvents = EventList.get(getActivity()).getEvents();
			
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		
		/*
		// add event
		SubMenu subMenu1 = menu.addSubMenu(R.id.menu_item_new_event);
		MenuItem subMenu1Item = subMenu1.getItem();
		subMenu1Item.setIcon(R.drawable.ic_action_new);
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
*/
		
		// camera drop down menu
		/*SubMenu subMenu2 = menu.addSubMenu(R.string.new_picture);

		subMenu2.add(R.string.take_photo);
		subMenu2.add(R.string.choose_existing);

		MenuItem subMenu2Item = subMenu2.getItem();
		subMenu2Item.setIcon(R.drawable.ic_action_camera);
		subMenu2Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);*/


		inflater.inflate(R.menu.wayd, menu);		
		
	}
	
	@Override
	//This method handles the action bar
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		//Handles + button to add event
		case R.id.menu_item_new_event:
			Event e = new Event();
			EventList.get(getActivity()).addEvent(e);
			Intent i = new Intent(getActivity(), EventPagerActivity.class);
			i.putExtra(EventFragment.EXTRA_EVENT_ID, e.getId());
			startActivityForResult(i,0);
			return true;
			
		
		case R.id.takePicture:
			return true;
		case R.id.chooseExisting:
			 return true;
			
		case R.id.action_help:	
			Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.manishkpr.viewpagerimagegallery");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			//change the content of " " with the package path to the class
		default:
			return super.onOptionsItemSelected(item);	
		}
				
			
	}
	
	//This method takes care of removing events
	//that are empty
	private void removeEmptyEvents(ArrayList<Event> e) {
		for(int i = 0; i < e.size(); i++) {
			if(e.get(i).getTitle() == null)
				e.remove(i);
			
		}		
	}
	
	//This method will take care of updating list of events
	//according to the day user chooses in the calendar
	private ArrayList<Event> eventsFromSpecifiedCalendar(ArrayList<Event> e) {
		ArrayList<Event> temp = (ArrayList<Event>) e.clone();
		
		for(int i = 0; i < temp.size(); i++) {
			//check if date of the Events list matches date from the
			//date specified in the calendar
			
			
		}
		
		return temp;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_wayd, parent, false);
		
		calendar = (CalendarView)v.findViewById(R.id.calendarView1);
		
		///calendar.setShownWeekCount(2);
	
		removeEmptyEvents(mEvents);
		
		EventAdapter adapter = new EventAdapter(mEvents);
		lv = (ListView)v.findViewById(R.id.listView1);
		lv.setAdapter(adapter);
		
	
		//Comment
		//Listener Handle the list view	   
		lv.setOnItemClickListener(new OnItemClickListener() {
			 
            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            	Event e = (Event)lv.getItemAtPosition(position);
            	
            	FragmentManager fm = getActivity().getSupportFragmentManager();
            	EventInfoFragment dialog = new EventInfoFragment(e.getTitle(), e.getLocation());
            	dialog.show(fm, EVENT_INFO_DIALOG);
              
            }

       }); 
		
		//Listener to handle calendar
	   calendar.setOnDateChangeListener(new OnDateChangeListener() {
		   public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
			  yearSelected = year;
			  monthSelected = month;
			  dayOfMonthSelected = dayOfMonth;
			  
			   
		   }
	   });
  
		
		return v;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
		
	}
	
	private class EventAdapter extends ArrayAdapter<Event> {
		public EventAdapter(ArrayList<Event> events) {
			super(getActivity(),0,events);
		}
		
		
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_event_item, null);
			}
			
			Event e = getItem(position);
			TextView titleTextView =
					(TextView)convertView.findViewById(R.id.event_list_item_titleTextView);
			
			titleTextView.setText(e.getTitle());
			
			TextView timeTextView =
					(TextView)convertView.findViewById(R.id.event_list_item_timeTextView);
			Calendar cal = Calendar.getInstance();
			cal.setTime(e.getTime());
			
			String t;
			
			if(cal.get(Calendar.MINUTE) < 10)
				t = cal.get(Calendar.HOUR_OF_DAY) + ":" + "0" + cal.get(Calendar.MINUTE);
			else
				t = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
			
			timeTextView.setText(t);
			
			
			
			return convertView;
		
		}
	
		
		
	}
}
