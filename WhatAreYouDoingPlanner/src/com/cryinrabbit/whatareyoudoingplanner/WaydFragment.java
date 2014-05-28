package com.cryinrabbit.whatareyoudoingplanner;

/*
 *This is the main menu of the app
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import android.annotation.SuppressLint;


import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.cryinrabbit.service.Findit;



public class WaydFragment extends ListFragment {
	
	private ArrayList<Event> mEvents;
	private static final String EVENT_INFO_DIALOG ="info";
	private static final String TAG = "EventListFragment";
	private ListView lv;
	private CalendarView calendar;
	private int yearSelected; //year selected by user
	private int monthSelected; //month selected by user
	private int dayOfMonthSelected; //day of month selected by user
	
	// help overlay
	Context ctx;
	
	// help overlay
	Context ctx;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		// help points to this
		//ctx = this.getActivity();

		setHasOptionsMenu(true);
		
		mEvents = EventList.get(getActivity()).getEvents();
		mSchedule = new MySchedule();
		
		//showOverLay();		
	}
	
	// help overlay method
		/*
		private void showOverLay() {
			final Dialog dialog = new Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar);
			dialog.setContentView(R.layout.overlay_help);
			LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.overlayLayout);
			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.dismiss();
				}
			});
			dialog.show();
		}*/
	
		
	}*/


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

			 
		case R.id.myschedule:
			FragmentManager fm = getActivity().getSupportFragmentManager();
			ClipboardManager manager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			//String x = manager.getText().toString();
			String x = manager.getText().toString();
			
		 if(pictureTaken) {
			Log.d("msg", "getting schedule info");
			//manager.setText("");
			Findit f=new Findit();
			HashSet<String> a=f.findWeek(x);
			int count=0;
			String[] schedule=new String[a.size()];
			Iterator iter = a.iterator();
			while (iter.hasNext()) {
				schedule[count++]=(String)iter.next();
			}
				
			 for(int ii=0;ii<schedule.length;ii++){
				if(f.findMo(schedule[ii])){
					Event iii = new Event();
						
					String event="Mo";
					String startTime=f.findStartTime(schedule[ii]);
						
					String endTime=f.findEndTime(schedule[ii]);
					System.out.print(event+"\t");
						
					Calendar cal = Calendar.getInstance();
					
					cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					//int hr = Integer.parseInt(startTime.split(":")[0]);
					//if(startTime.charAt(startTime.length()-2) == 'P')
//						hr += 12;
					//cal.set(2014, 5, 27, hr, 0);
						
						
						
					iii.setTime(cal.getTime());
					iii.setTitle("Class " + (classNumber++));
						
						
					mEvents.add(iii);
						
						
						
					System.out.print(startTime+"\t");
						
						
					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}
					
				if(f.findTu(schedule[ii])){
					String event="Tu";
					String startTime=f.findStartTime(schedule[ii]);
					String endTime=f.findEndTime(schedule[ii]);
					System.out.print(event+"\t");
						
					Event iii = new Event();
						
					Calendar cal = Calendar.getInstance();
						cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					//int hr = Integer.parseInt(startTime.split(":")[0]);
					//if(startTime.charAt(startTime.length()-2) == 'P')
						//hr += 12;
					//cal.set(2014, 5, 27, hr, 0);
						
						
						
						iii.setTime(cal.getTime());
						iii.setTitle("Class " + (classNumber++));
						
						
						mEvents.add(iii);
							
						
						
						
						
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}
					
					/*if(f.findWe(schedule[ii])){
						String event="We";
						String startTime=f.findStartTime(schedule[ii]);
						
							
						Event iii = new Event();
						Calendar cal = Calendar.getInstance();
						cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
						
						
						
						iii.setTime(cal.getTime());
						iii.setTitle("Class");
						
				
						mEvents.add(iii);
						
						
						
						
						
						
						
						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}*/
					
					/*if(f.findTh(schedule[ii])){
						String event="Th";
						String startTime=f.findStartTime(schedule[ii]);
						

						Event iii = new Event();
						Calendar cal = Calendar.getInstance();
						cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
						
						
						
						iii.setTime(cal.getTime());
						iii.setTitle("Class");
						
						//Check if event already has that time
						
						mEvents.add(iii);
						
						
						
						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}*/
					
					if(f.findFr(schedule[ii])){
						String event="Fr";
						String startTime=f.findStartTime(schedule[ii]);
						
						Event iii = new Event();
						Calendar cal = Calendar.getInstance();
						cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
						//int hr = Integer.parseInt(startTime.split(":")[0]);
						//if(startTime.charAt(startTime.length()-2) == 'P')
//							hr += 12;
						//cal.set(2014, 5, 27, hr, 0);
						
						
						
						iii.setTime(cal.getTime());
						iii.setTitle("Class");
						
						//Check if event already has that time
						for(int j = 0; j < mEvents.size(); j++) {
							if(mEvents.get(j).getTime().equals(cal.getTime())) {
								break;
							}
						}
						mEvents.add(iii);
						
						
						
						
						
						
						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}
					
					if(f.findSa(schedule[ii])){
						String event="Sa";
						String startTime=f.findStartTime(schedule[ii]);
						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}
					
					
				}
			 
			 pictureTaken = false;
			}
			
			
			
			ScheduleInfoFragment dialog = new ScheduleInfoFragment("My Schedule",manager.getText().toString());
			//manager.setText("");
        	dialog.show(fm, SCHEDULE_INFO_DIALOG);
        	
			
			return true;
			
			
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
	
	//Test code
	/*public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("msg", "called");
		if(requestCode == 1) {
			if(resultCode == Activity.RESULT_OK) {
				Log.d("msg", "called");
				mSchedule.setSchedule(data.getExtras().getString("MySchedule"));
			}
		}

	        
	}*/
	
	
	
	
	
	@SuppressLint("NewApi") @Override
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
		
	
		
// chi

		FragmentManager fm = getActivity().getSupportFragmentManager();
		ClipboardManager manager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
		//String x = manager.getText().toString();
		String x = manager.getText().toString();
		
	 if(pictureTaken) {
		Log.d("msg", "getting schedule info");
		//manager.setText("");
		Findit f=new Findit();
		HashSet<String> a=f.findWeek(x);
		int count=0;
		String[] schedule=new String[a.size()];
		Iterator iter = a.iterator();
		while (iter.hasNext()) {
			schedule[count++]=(String)iter.next();
		}
			
		 for(int ii=0;ii<schedule.length;ii++){
			if(f.findMo(schedule[ii])){
				Event iii = new Event();
					
				String event="Mo";
				String startTime=f.findStartTime(schedule[ii]);
					
				String endTime=f.findEndTime(schedule[ii]);
				System.out.print(event+"\t");
					
				Calendar calM = Calendar.getInstance();
				calM.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					
					
					
				iii.setTime(calM.getTime());
				iii.setTitle("Class " + (classNumber++));
					
					
				mEvents.add(iii);
					
					
					
				System.out.print(startTime+"\t");
					
					
				System.out.println(endTime);
				((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
			}
				
			if(f.findTu(schedule[ii])){
				String event="Tu";
				String startTime=f.findStartTime(schedule[ii]);
				String endTime=f.findEndTime(schedule[ii]);
				System.out.print(event+"\t");
					
				Event iii = new Event();
					
				Calendar calT = Calendar.getInstance();
					calT.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					
					
					
					iii.setTime(calT.getTime());
					iii.setTitle("Class " + (classNumber++));
					
					
					mEvents.add(iii);
						
					
					
					
					
					System.out.print(startTime+"\t");
					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}
				
				/*if(f.findWe(schedule[ii])){
					String event="We";
					String startTime=f.findStartTime(schedule[ii]);
					
						
					Event iii = new Event();
					Calendar cal = Calendar.getInstance();
					cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					
					
					
					iii.setTime(cal.getTime());
					iii.setTitle("Class");
					
			
					mEvents.add(iii);
					
					
					
					
					
					
					
					String endTime=f.findEndTime(schedule[ii]);
					System.out.print(event+"\t");
					System.out.print(startTime+"\t");
					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}*/
				
				/*if(f.findTh(schedule[ii])){
					String event="Th";
					String startTime=f.findStartTime(schedule[ii]);
					

					Event iii = new Event();
					Calendar cal = Calendar.getInstance();
					cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					
					
					
					iii.setTime(cal.getTime());
					iii.setTitle("Class");
					
					//Check if event already has that time
					
					mEvents.add(iii);
					
					
					
					String endTime=f.findEndTime(schedule[ii]);
					System.out.print(event+"\t");
					System.out.print(startTime+"\t");
					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}*/
				
				if(f.findFr(schedule[ii])){
					String event="Fr";
					String startTime=f.findStartTime(schedule[ii]);
					
					Event iii = new Event();
					Calendar calF = Calendar.getInstance();
					calF.set(2014, 5, 27, (int)startTime.charAt(0), 0);
					
					
					
					iii.setTime(calF.getTime());
					iii.setTitle("Class");
					
					//Check if event already has that time
					for(int j = 0; j < mEvents.size(); j++) {
						if(mEvents.get(j).getTime().equals(cal.getTime())) {
							break;
						}
					}
					mEvents.add(iii);
					
					
					
					
					
					
					String endTime=f.findEndTime(schedule[ii]);
					System.out.print(event+"\t");
					System.out.print(startTime+"\t");
					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}
				
				if(f.findSa(schedule[ii])){
					String event="Sa";
					String startTime=f.findStartTime(schedule[ii]);
					String endTime=f.findEndTime(schedule[ii]);
					System.out.print(event+"\t");
					System.out.print(startTime+"\t");
					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}
				
				
			}
		 
		 pictureTaken = false;
		}
	
		
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
			
			//timeTextView.setText(t+" PM");
			
			
			
			return convertView;
		
		}
	
		
		
		
		
	}
}
