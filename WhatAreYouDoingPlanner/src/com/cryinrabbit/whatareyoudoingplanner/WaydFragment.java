package com.cryinrabbit.whatareyoudoingplanner;

/*
 *This is the main menu of the app
 *Useless comment
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cryinrabbit.service.Findit;
//import com.roomorama.caldroid.CaldroidFragment;






import android.content.SharedPreferences;


@SuppressLint("NewApi")
public class WaydFragment extends ListFragment {

	private ArrayList<Event> mEvents;
	private static final String EVENT_INFO_DIALOG ="info";
	private static final String TAG = "EventListFragment";
	private ListView lv;
	private CalendarView calendar;
	private int yearSelected; //year selected by user
	private int monthSelected; //month selected by user
	private int dayOfMonthSelected; //day of month selected by user
	private static final String SCHEDULE_INFO_DIALOG="schedule_info";
	private MySchedule mSchedule;
    private static Date date = new Date();
    private int classNumber = 1;
    private static boolean listAlreadyfilled = false;
    private static boolean pictureTaken = false;
    // ** help page overlay initialize
    private static final String PREFS_NAME = "MyPrefsFile";
    Context ctx;
    // ** end help initialize
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		mEvents = EventList.get(getActivity()).getEvents();
		mSchedule = new MySchedule();

		// **help page overlay: Restore preferences
		SharedPreferences settings = this.getActivity().getSharedPreferences(PREFS_NAME, 0);
		boolean dialogShown = settings.getBoolean("dialogShown", false);

		// help points to this class
		ctx = this.getActivity();
		setHasOptionsMenu(true);
				
		// if first time showing the dialog
		if (!dialogShown) {
			showOverLay();
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("dialogShown", true);
			editor.commit();
		}
		// ** end help page overlay
	}
	
	// help overlay method
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
		}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

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

			//Intent intent = getActivity().getPackageManager()
			//.getLaunchIntentForPackage("edu.sfsu.cs.orange.ocr");

			Intent intent = new Intent();
			intent.setClassName("edu.sfsu.cs.orange.ocr", "edu.sfsu.cs.orange.ocr.CaptureActivity");

			//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			//test code
			//Bundle b = new Bundle();
			//b.putString("testString", "HelloWorld");
			//intent.putExtra("MySchedule", b);

			pictureTaken = true;	
			//mEvents.clear();
			startActivityForResult(intent,1);
			
/*			
			FragmentManager fm = getActivity().getSupportFragmentManager();
			ClipboardManager manager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			//String x = manager.getText().toString();
			String x = manager.getText().toString();

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
				
				for(int ii=0, mon=0, tue=1, wed=2, thu=3, fri=4;
						ii<schedule.length;ii++){
					
					//String title=f.getTitle(schedule[ii]);
					
					if(f.findMo(schedule[ii])){
						Event iii1 = new Event();
						Event iii2 = new Event();
						Event iii3 = new Event();
						Event iii4 = new Event();
						Event iii5 = new Event();
						Event iii6 = new Event();
						Event iii7 = new Event();
						Event iii8 = new Event();
						Event iii9 = new Event();
						Event iii10 = new Event();

						String event="Mo";
						String startTime=f.findStartTime(schedule[ii]);

						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");

						Calendar cal1 = Calendar.getInstance();
						
						//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
						/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
						cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
/*						
						cal1.set(Calendar.MONTH, 2);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 31);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						

						iii1.setTime(cal1.getTime());
						iii1.setStartDate(cal1.getTime());
						iii1.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 7);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii2.setTime(cal1.getTime());
						iii2.setStartDate(cal1.getTime());
						iii2.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 14);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii3.setTime(cal1.getTime());
						iii3.setStartDate(cal1.getTime());
						iii3.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 21);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii4.setTime(cal1.getTime());
						iii4.setStartDate(cal1.getTime());
						iii4.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 28);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii5.setTime(cal1.getTime());
						iii5.setStartDate(cal1.getTime());
						iii5.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 5);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii6.setTime(cal1.getTime());
						iii6.setStartDate(cal1.getTime());
						iii6.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 12);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii7.setTime(cal1.getTime());
						iii7.setStartDate(cal1.getTime());
						iii7.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 19);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii8.setTime(cal1.getTime());
						iii8.setStartDate(cal1.getTime());
						iii8.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 26);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii9.setTime(cal1.getTime());
						iii9.setStartDate(cal1.getTime());
						iii9.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 5);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 2);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii10.setTime(cal1.getTime());
						iii10.setStartDate(cal1.getTime());
						iii10.setTitle(f.getTitle(schedule[ii]));
				
						mEvents.add(iii1);
						mEvents.add(iii2);
						mEvents.add(iii3);
						mEvents.add(iii4);
						mEvents.add(iii5);
						mEvents.add(iii6);
						mEvents.add(iii7);
						mEvents.add(iii8);
						mEvents.add(iii9);
						mEvents.add(iii10);

						System.out.print(startTime+"\t");

						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}

					if(f.findTu(schedule[ii])){
						Event iii1 = new Event();
						Event iii2 = new Event();
						Event iii3 = new Event();
						Event iii4 = new Event();
						Event iii5 = new Event();
						Event iii6 = new Event();
						Event iii7 = new Event();
						Event iii8 = new Event();
						Event iii9 = new Event();
						Event iii10 = new Event();

						String event="Mo";
						String startTime=f.findStartTime(schedule[ii]);

						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");

						Calendar cal1 = Calendar.getInstance();
						
						//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
						/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
						cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
/*						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 1);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						

						iii1.setTime(cal1.getTime());
						iii1.setStartDate(cal1.getTime());
						iii1.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 8);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii2.setTime(cal1.getTime());
						iii2.setStartDate(cal1.getTime());
						iii2.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 15);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii3.setTime(cal1.getTime());
						iii3.setStartDate(cal1.getTime());
						iii3.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 22);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii4.setTime(cal1.getTime());
						iii4.setStartDate(cal1.getTime());
						iii4.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 29);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii5.setTime(cal1.getTime());
						iii5.setStartDate(cal1.getTime());
						iii5.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 6);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii6.setTime(cal1.getTime());
						iii6.setStartDate(cal1.getTime());
						iii6.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 13);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii7.setTime(cal1.getTime());
						iii7.setStartDate(cal1.getTime());
						iii7.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 20);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii8.setTime(cal1.getTime());
						iii8.setStartDate(cal1.getTime());
						iii8.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 27);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii9.setTime(cal1.getTime());
						iii9.setStartDate(cal1.getTime());
						iii9.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 5);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 3);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii10.setTime(cal1.getTime());
						iii10.setStartDate(cal1.getTime());
						iii10.setTitle(f.getTitle(schedule[ii]));
				
						mEvents.add(iii1);
						mEvents.add(iii2);
						mEvents.add(iii3);
						mEvents.add(iii4);
						mEvents.add(iii5);
						mEvents.add(iii6);
						mEvents.add(iii7);
						mEvents.add(iii8);
						mEvents.add(iii9);
						mEvents.add(iii10);

						System.out.print(startTime+"\t");

						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}

						if(f.findWe(schedule[ii])){
							Event iii1 = new Event();
							Event iii2 = new Event();
							Event iii3 = new Event();
							Event iii4 = new Event();
							Event iii5 = new Event();
							Event iii6 = new Event();
							Event iii7 = new Event();
							Event iii8 = new Event();
							Event iii9 = new Event();
							Event iii10 = new Event();

							String event="Mo";
							String startTime=f.findStartTime(schedule[ii]);

							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");

							Calendar cal1 = Calendar.getInstance();
							
							//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
							/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
							cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
/*							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 2);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							

							iii1.setTime(cal1.getTime());
							iii1.setStartDate(cal1.getTime());
							iii1.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 9);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii2.setTime(cal1.getTime());
							iii2.setStartDate(cal1.getTime());
							iii2.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 16);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii3.setTime(cal1.getTime());
							iii3.setStartDate(cal1.getTime());
							iii3.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 23);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii4.setTime(cal1.getTime());
							iii4.setStartDate(cal1.getTime());
							iii4.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 30);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii5.setTime(cal1.getTime());
							iii5.setStartDate(cal1.getTime());
							iii5.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 7);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii6.setTime(cal1.getTime());
							iii6.setStartDate(cal1.getTime());
							iii6.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 14);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii7.setTime(cal1.getTime());
							iii7.setStartDate(cal1.getTime());
							iii7.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 21);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii8.setTime(cal1.getTime());
							iii8.setStartDate(cal1.getTime());
							iii8.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 28);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii9.setTime(cal1.getTime());
							iii9.setStartDate(cal1.getTime());
							iii9.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 5);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 4);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii10.setTime(cal1.getTime());
							iii10.setStartDate(cal1.getTime());
							iii10.setTitle(f.getTitle(schedule[ii]));
					
							mEvents.add(iii1);
							mEvents.add(iii2);
							mEvents.add(iii3);
							mEvents.add(iii4);
							mEvents.add(iii5);
							mEvents.add(iii6);
							mEvents.add(iii7);
							mEvents.add(iii8);
							mEvents.add(iii9);
							mEvents.add(iii10);

							System.out.print(startTime+"\t");

							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}

						if(f.findTh(schedule[ii])){
							Event iii1 = new Event();
							Event iii2 = new Event();
							Event iii3 = new Event();
							Event iii4 = new Event();
							Event iii5 = new Event();
							Event iii6 = new Event();
							Event iii7 = new Event();
							Event iii8 = new Event();
							Event iii9 = new Event();
							Event iii10 = new Event();

							String event="Mo";
							String startTime=f.findStartTime(schedule[ii]);

							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");

							Calendar cal1 = Calendar.getInstance();
							
							//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
							/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
							cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
/*							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 3);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							

							iii1.setTime(cal1.getTime());
							iii1.setStartDate(cal1.getTime());
							iii1.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 10);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii2.setTime(cal1.getTime());
							iii2.setStartDate(cal1.getTime());
							iii2.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 17);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii3.setTime(cal1.getTime());
							iii3.setStartDate(cal1.getTime());
							iii3.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 24);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii4.setTime(cal1.getTime());
							iii4.setStartDate(cal1.getTime());
							iii4.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 1);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii5.setTime(cal1.getTime());
							iii5.setStartDate(cal1.getTime());
							iii5.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 8);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii6.setTime(cal1.getTime());
							iii6.setStartDate(cal1.getTime());
							iii6.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 15);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii7.setTime(cal1.getTime());
							iii7.setStartDate(cal1.getTime());
							iii7.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 22);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii8.setTime(cal1.getTime());
							iii8.setStartDate(cal1.getTime());
							iii8.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 29);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii9.setTime(cal1.getTime());
							iii9.setStartDate(cal1.getTime());
							iii9.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 5);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 5);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii10.setTime(cal1.getTime());
							iii10.setStartDate(cal1.getTime());
							iii10.setTitle(f.getTitle(schedule[ii]));
					
							mEvents.add(iii1);
							mEvents.add(iii2);
							mEvents.add(iii3);
							mEvents.add(iii4);
							mEvents.add(iii5);
							mEvents.add(iii6);
							mEvents.add(iii7);
							mEvents.add(iii8);
							mEvents.add(iii9);
							mEvents.add(iii10);

							System.out.print(startTime+"\t");

							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}

						if(f.findFr(schedule[ii])){
							Event iii1 = new Event();
							Event iii2 = new Event();
							Event iii3 = new Event();
							Event iii4 = new Event();
							Event iii5 = new Event();
							Event iii6 = new Event();
							Event iii7 = new Event();
							Event iii8 = new Event();
							Event iii9 = new Event();
							Event iii10 = new Event();

							String event="Mo";
							String startTime=f.findStartTime(schedule[ii]);

							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");

							Calendar cal1 = Calendar.getInstance();
							
							//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
							/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
							cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
/*							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 4);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							

							iii1.setTime(cal1.getTime());
							iii1.setStartDate(cal1.getTime());
							iii1.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 11);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii2.setTime(cal1.getTime());
							iii2.setStartDate(cal1.getTime());
							iii2.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 18);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii3.setTime(cal1.getTime());
							iii3.setStartDate(cal1.getTime());
							iii3.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 25);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii4.setTime(cal1.getTime());
							iii4.setStartDate(cal1.getTime());
							iii4.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 2);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii5.setTime(cal1.getTime());
							iii5.setStartDate(cal1.getTime());
							iii5.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 9);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii6.setTime(cal1.getTime());
							iii6.setStartDate(cal1.getTime());
							iii6.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 16);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii7.setTime(cal1.getTime());
							iii7.setStartDate(cal1.getTime());
							iii7.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 23);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii8.setTime(cal1.getTime());
							iii8.setStartDate(cal1.getTime());
							iii8.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 30);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii9.setTime(cal1.getTime());
							iii9.setStartDate(cal1.getTime());
							iii9.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 5);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 6);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii10.setTime(cal1.getTime());
							iii10.setStartDate(cal1.getTime());
							iii10.setTitle(f.getTitle(schedule[ii]));
					
							mEvents.add(iii1);
							mEvents.add(iii2);
							mEvents.add(iii3);
							mEvents.add(iii4);
							mEvents.add(iii5);
							mEvents.add(iii6);
							mEvents.add(iii7);
							mEvents.add(iii8);
							mEvents.add(iii9);
							mEvents.add(iii10);

							System.out.print(startTime+"\t");

							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}
	/*
						if(f.findSa(schedule[ii])){
							String event="Sa";
							String startTime=f.findStartTime(schedule[ii]);
							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");
							System.out.print(startTime+"\t");
							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}
	*/
//					}
			
			return true;
		case R.id.chooseExisting:
			 return true;

		case R.id.myschedule:
			FragmentManager fm = getActivity().getSupportFragmentManager();
			ClipboardManager manager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			//String x = manager.getText().toString();
			String x = manager.getText().toString();
			if(pictureTaken)
			{

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
				
				for(int ii=0, mon=0, tue=1, wed=2, thu=3, fri=4;
						ii<schedule.length;ii++){
					
					//String title=f.getTitle(schedule[ii]);
					
					if(f.findMo(schedule[ii])){
						Event iii1 = new Event();
						Event iii2 = new Event();
						Event iii3 = new Event();
						Event iii4 = new Event();
						Event iii5 = new Event();
						Event iii6 = new Event();
						Event iii7 = new Event();
						Event iii8 = new Event();
						Event iii9 = new Event();
						Event iii10 = new Event();

						String event="Mo";
						String startTime=f.findStartTime(schedule[ii]);

						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");

						Calendar cal1 = Calendar.getInstance();
						
						//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
						/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
						cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
						
						cal1.set(Calendar.MONTH, 2);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 31);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						

						iii1.setTime(cal1.getTime());
						iii1.setStartDate(cal1.getTime());
						iii1.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 7);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii2.setTime(cal1.getTime());
						iii2.setStartDate(cal1.getTime());
						iii2.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 14);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii3.setTime(cal1.getTime());
						iii3.setStartDate(cal1.getTime());
						iii3.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 21);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii4.setTime(cal1.getTime());
						iii4.setStartDate(cal1.getTime());
						iii4.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 28);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii5.setTime(cal1.getTime());
						iii5.setStartDate(cal1.getTime());
						iii5.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 5);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii6.setTime(cal1.getTime());
						iii6.setStartDate(cal1.getTime());
						iii6.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 12);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii7.setTime(cal1.getTime());
						iii7.setStartDate(cal1.getTime());
						iii7.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 19);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii8.setTime(cal1.getTime());
						iii8.setStartDate(cal1.getTime());
						iii8.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 26);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii9.setTime(cal1.getTime());
						iii9.setStartDate(cal1.getTime());
						iii9.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 5);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 2);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii10.setTime(cal1.getTime());
						iii10.setStartDate(cal1.getTime());
						iii10.setTitle(f.getTitle(schedule[ii]));
				
						mEvents.add(iii1);
						mEvents.add(iii2);
						mEvents.add(iii3);
						mEvents.add(iii4);
						mEvents.add(iii5);
						mEvents.add(iii6);
						mEvents.add(iii7);
						mEvents.add(iii8);
						mEvents.add(iii9);
						mEvents.add(iii10);

						System.out.print(startTime+"\t");

						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}

					if(f.findTu(schedule[ii])){
						Event iii1 = new Event();
						Event iii2 = new Event();
						Event iii3 = new Event();
						Event iii4 = new Event();
						Event iii5 = new Event();
						Event iii6 = new Event();
						Event iii7 = new Event();
						Event iii8 = new Event();
						Event iii9 = new Event();
						Event iii10 = new Event();

						String event="Mo";
						String startTime=f.findStartTime(schedule[ii]);

						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");

						Calendar cal1 = Calendar.getInstance();
						
						//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
						/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
						cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
						cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
						cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 1);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						

						iii1.setTime(cal1.getTime());
						iii1.setStartDate(cal1.getTime());
						iii1.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 8);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii2.setTime(cal1.getTime());
						iii2.setStartDate(cal1.getTime());
						iii2.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 15);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii3.setTime(cal1.getTime());
						iii3.setStartDate(cal1.getTime());
						iii3.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 22);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii4.setTime(cal1.getTime());
						iii4.setStartDate(cal1.getTime());
						iii4.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 3);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 29);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii5.setTime(cal1.getTime());
						iii5.setStartDate(cal1.getTime());
						iii5.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 6);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii6.setTime(cal1.getTime());
						iii6.setStartDate(cal1.getTime());
						iii6.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 13);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii7.setTime(cal1.getTime());
						iii7.setStartDate(cal1.getTime());
						iii7.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 20);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii8.setTime(cal1.getTime());
						iii8.setStartDate(cal1.getTime());
						iii8.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 4);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 27);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii9.setTime(cal1.getTime());
						iii9.setStartDate(cal1.getTime());
						iii9.setTitle(f.getTitle(schedule[ii]));
						
						cal1.set(Calendar.MONTH, 5);
						cal1.set(Calendar.YEAR, 2014);
						cal1.set(Calendar.DAY_OF_MONTH, 3);
						cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
						cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
						
						
						iii10.setTime(cal1.getTime());
						iii10.setStartDate(cal1.getTime());
						iii10.setTitle(f.getTitle(schedule[ii]));
				
						mEvents.add(iii1);
						mEvents.add(iii2);
						mEvents.add(iii3);
						mEvents.add(iii4);
						mEvents.add(iii5);
						mEvents.add(iii6);
						mEvents.add(iii7);
						mEvents.add(iii8);
						mEvents.add(iii9);
						mEvents.add(iii10);

						System.out.print(startTime+"\t");

						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}

						if(f.findWe(schedule[ii])){
							Event iii1 = new Event();
							Event iii2 = new Event();
							Event iii3 = new Event();
							Event iii4 = new Event();
							Event iii5 = new Event();
							Event iii6 = new Event();
							Event iii7 = new Event();
							Event iii8 = new Event();
							Event iii9 = new Event();
							Event iii10 = new Event();

							String event="Mo";
							String startTime=f.findStartTime(schedule[ii]);

							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");

							Calendar cal1 = Calendar.getInstance();
							
							//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
							/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
							cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 2);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							

							iii1.setTime(cal1.getTime());
							iii1.setStartDate(cal1.getTime());
							iii1.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 9);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii2.setTime(cal1.getTime());
							iii2.setStartDate(cal1.getTime());
							iii2.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 16);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii3.setTime(cal1.getTime());
							iii3.setStartDate(cal1.getTime());
							iii3.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 23);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii4.setTime(cal1.getTime());
							iii4.setStartDate(cal1.getTime());
							iii4.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 30);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii5.setTime(cal1.getTime());
							iii5.setStartDate(cal1.getTime());
							iii5.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 7);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii6.setTime(cal1.getTime());
							iii6.setStartDate(cal1.getTime());
							iii6.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 14);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii7.setTime(cal1.getTime());
							iii7.setStartDate(cal1.getTime());
							iii7.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 21);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii8.setTime(cal1.getTime());
							iii8.setStartDate(cal1.getTime());
							iii8.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 28);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii9.setTime(cal1.getTime());
							iii9.setStartDate(cal1.getTime());
							iii9.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 5);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 4);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii10.setTime(cal1.getTime());
							iii10.setStartDate(cal1.getTime());
							iii10.setTitle(f.getTitle(schedule[ii]));
					
							mEvents.add(iii1);
							mEvents.add(iii2);
							mEvents.add(iii3);
							mEvents.add(iii4);
							mEvents.add(iii5);
							mEvents.add(iii6);
							mEvents.add(iii7);
							mEvents.add(iii8);
							mEvents.add(iii9);
							mEvents.add(iii10);

							System.out.print(startTime+"\t");

							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}

						if(f.findTh(schedule[ii])){
							Event iii1 = new Event();
							Event iii2 = new Event();
							Event iii3 = new Event();
							Event iii4 = new Event();
							Event iii5 = new Event();
							Event iii6 = new Event();
							Event iii7 = new Event();
							Event iii8 = new Event();
							Event iii9 = new Event();
							Event iii10 = new Event();

							String event="Mo";
							String startTime=f.findStartTime(schedule[ii]);

							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");

							Calendar cal1 = Calendar.getInstance();
							
							//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
							/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
							cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 3);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							

							iii1.setTime(cal1.getTime());
							iii1.setStartDate(cal1.getTime());
							iii1.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 10);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii2.setTime(cal1.getTime());
							iii2.setStartDate(cal1.getTime());
							iii2.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 17);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii3.setTime(cal1.getTime());
							iii3.setStartDate(cal1.getTime());
							iii3.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 24);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii4.setTime(cal1.getTime());
							iii4.setStartDate(cal1.getTime());
							iii4.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 1);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii5.setTime(cal1.getTime());
							iii5.setStartDate(cal1.getTime());
							iii5.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 8);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii6.setTime(cal1.getTime());
							iii6.setStartDate(cal1.getTime());
							iii6.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 15);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii7.setTime(cal1.getTime());
							iii7.setStartDate(cal1.getTime());
							iii7.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 22);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii8.setTime(cal1.getTime());
							iii8.setStartDate(cal1.getTime());
							iii8.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 29);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii9.setTime(cal1.getTime());
							iii9.setStartDate(cal1.getTime());
							iii9.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 5);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 5);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii10.setTime(cal1.getTime());
							iii10.setStartDate(cal1.getTime());
							iii10.setTitle(f.getTitle(schedule[ii]));
					
							mEvents.add(iii1);
							mEvents.add(iii2);
							mEvents.add(iii3);
							mEvents.add(iii4);
							mEvents.add(iii5);
							mEvents.add(iii6);
							mEvents.add(iii7);
							mEvents.add(iii8);
							mEvents.add(iii9);
							mEvents.add(iii10);

							System.out.print(startTime+"\t");

							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}

						if(f.findFr(schedule[ii])){
							Event iii1 = new Event();
							Event iii2 = new Event();
							Event iii3 = new Event();
							Event iii4 = new Event();
							Event iii5 = new Event();
							Event iii6 = new Event();
							Event iii7 = new Event();
							Event iii8 = new Event();
							Event iii9 = new Event();
							Event iii10 = new Event();

							String event="Mo";
							String startTime=f.findStartTime(schedule[ii]);

							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");

							Calendar cal1 = Calendar.getInstance();
							
							//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
							/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
							cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
							cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
							cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 4);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							

							iii1.setTime(cal1.getTime());
							iii1.setStartDate(cal1.getTime());
							iii1.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 11);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii2.setTime(cal1.getTime());
							iii2.setStartDate(cal1.getTime());
							iii2.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 18);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii3.setTime(cal1.getTime());
							iii3.setStartDate(cal1.getTime());
							iii3.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 3);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 25);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii4.setTime(cal1.getTime());
							iii4.setStartDate(cal1.getTime());
							iii4.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 2);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii5.setTime(cal1.getTime());
							iii5.setStartDate(cal1.getTime());
							iii5.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 9);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii6.setTime(cal1.getTime());
							iii6.setStartDate(cal1.getTime());
							iii6.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 16);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii7.setTime(cal1.getTime());
							iii7.setStartDate(cal1.getTime());
							iii7.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 23);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii8.setTime(cal1.getTime());
							iii8.setStartDate(cal1.getTime());
							iii8.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 4);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 30);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii9.setTime(cal1.getTime());
							iii9.setStartDate(cal1.getTime());
							iii9.setTitle(f.getTitle(schedule[ii]));
							
							cal1.set(Calendar.MONTH, 5);
							cal1.set(Calendar.YEAR, 2014);
							cal1.set(Calendar.DAY_OF_MONTH, 6);
							cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split(":")[0]));
							cal1.set(Calendar.MINUTE, Integer.parseInt(startTime.split(":")[1]));
							
							
							iii10.setTime(cal1.getTime());
							iii10.setStartDate(cal1.getTime());
							iii10.setTitle(f.getTitle(schedule[ii]));
					
							mEvents.add(iii1);
							mEvents.add(iii2);
							mEvents.add(iii3);
							mEvents.add(iii4);
							mEvents.add(iii5);
							mEvents.add(iii6);
							mEvents.add(iii7);
							mEvents.add(iii8);
							mEvents.add(iii9);
							mEvents.add(iii10);

							System.out.print(startTime+"\t");

							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}
	/*
						if(f.findSa(schedule[ii])){
							String event="Sa";
							String startTime=f.findStartTime(schedule[ii]);
							String endTime=f.findEndTime(schedule[ii]);
							System.out.print(event+"\t");
							System.out.print(startTime+"\t");
							System.out.println(endTime);
							((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
						}
	*/
					}

	        	pictureTaken = false;


	        	//ClipData data = ClipData.newPlainText("", "");
	        	//manager.setPrimaryClip(data);
			
			
/*
			FragmentManager fm1 = getActivity().getSupportFragmentManager();
			ClipboardManager manager1 = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			//String x = manager.getText().toString();
			String x1 = manager1.getText().toString();
			
/*
		 if(pictureTaken) {
			Log.d("msg", "getting schedule info");
			//manager.setText("");
			Findit f1=new Findit();
			HashSet<String> a1=f1.findWeek(x1);
			int count1=0;
			String[] schedule1=new String[a1.size()];
			Iterator iter1 = a1.iterator();
			while (iter1.hasNext()) {
				schedule1[count1++]=(String)iter1.next();
			}

			for(int ii=0;ii<schedule1.length;ii++){
				
				//String title=f.getTitle(schedule[ii]);
				
				if(f1.findMo(schedule1[ii])){
					Event iii1 = new Event();
					Event iii2 = new Event();
					Event iii3 = new Event();
					Event iii4 = new Event();
					Event iii5 = new Event();
					Event iii6 = new Event();
					Event iii7 = new Event();
					Event iii8 = new Event();
					Event iii9 = new Event();
					Event iii10 = new Event();

					String event="Mo";
					String startTime=f1.findStartTime(schedule1[ii]);

					String endTime=f1.findEndTime(schedule1[ii]);
					System.out.print(event+"\t");

					Calendar cal1 = Calendar.getInstance();
					
					//cal.set(2014, 8, 2, (int)startTime.charAt(0), 0);
					/*cal.set(2014, 6, 9, (int)startTime.charAt(0), 0);
					cal.set(2014, 6, 16, (int)startTime.charAt(0), 0);
					cal.set(2014, 6, 23, (int)startTime.charAt(0), 0);
					cal.set(2014, 7, 30, (int)startTime.charAt(0), 0);
					cal.set(2014, 7, 11, (int)startTime.charAt(0), 0);
					cal.set(2014, 7, 18, (int)startTime.charAt(0), 0);
					cal.set(2014, 7, 25, (int)startTime.charAt(0), 0);
					cal.set(2014, 8, 1, (int)startTime.charAt(0), 0);*/
/*					
					cal1.set(Calendar.MONTH, 3);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 31);
					

					iii1.setTime(cal1.getTime());
					iii1.setStartDate(cal1.getTime());
					iii1.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 5);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 9);
					
					iii2.setTime(cal1.getTime());
					iii2.setStartDate(cal1.getTime());
					iii2.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 5);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 16);
					
					iii3.setTime(cal1.getTime());
					iii3.setStartDate(cal1.getTime());
					iii3.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 5);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 23);
					
					iii4.setTime(cal1.getTime());
					iii4.setStartDate(cal1.getTime());
					iii4.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 5);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 30);
					
					iii5.setTime(cal1.getTime());
					iii5.setStartDate(cal1.getTime());
					iii5.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 6);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 7);
					
					iii6.setTime(cal1.getTime());
					iii6.setStartDate(cal1.getTime());
					iii6.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 6);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 14);
					
					iii7.setTime(cal1.getTime());
					iii7.setStartDate(cal1.getTime());
					iii7.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 6);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 21);
					
					iii8.setTime(cal1.getTime());
					iii8.setStartDate(cal1.getTime());
					iii8.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 6);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 28);
					
					iii9.setTime(cal1.getTime());
					iii9.setStartDate(cal1.getTime());
					iii9.setTitle(f1.getTitle(schedule1[ii]));
					
					cal1.set(Calendar.MONTH, 7);
					cal1.set(Calendar.YEAR, 2014);
					cal1.set(Calendar.DAY_OF_MONTH, 4);
					
					iii10.setTime(cal1.getTime());
					iii10.setStartDate(cal1.getTime());
					iii10.setTitle(f1.getTitle(schedule1[ii]));
					
			
					mEvents.add(iii1);
					mEvents.add(iii2);
					mEvents.add(iii3);
					mEvents.add(iii4);
					mEvents.add(iii5);
					mEvents.add(iii6);
					mEvents.add(iii7);
					mEvents.add(iii8);
					mEvents.add(iii9);
					
					



					System.out.print(startTime+"\t");


					System.out.println(endTime);
					((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
				}

				if(f1.findTu(schedule1[ii])){
					String event="Tu";
					String startTime=f1.findStartTime(schedule1[ii]);
					String endTime=f1.findEndTime(schedule1[ii]);
					System.out.print(event+"\t");

					Event iii = new Event();

					Calendar cal = Calendar.getInstance();
						cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);



						iii.setTime(cal.getTime());
						iii.setTitle("Class " + (classNumber++));


						mEvents.add(iii);





						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}
*/
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
/*
					if(f1.findFr(schedule1[ii])){
						String event="Fr";
						String startTime=f1.findStartTime(schedule1[ii]);

						Event iii = new Event();
						Calendar cal = Calendar.getInstance();
						cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);



						iii.setTime(cal.getTime());
						iii.setTitle("Class");

						//Check if event already has that time
						for(int j = 0; j < mEvents.size(); j++) {
							if(mEvents.get(j).getTime().equals(cal.getTime())) {
								break;
							}
						}
						mEvents.add(iii);






						String endTime=f1.findEndTime(schedule1[ii]);
						System.out.print(event+"\t");
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}
*/
/*
					if(f.findSa(schedule[ii])){
						String event="Sa";
						String startTime=f.findStartTime(schedule[ii]);
						String endTime=f.findEndTime(schedule[ii]);
						System.out.print(event+"\t");
						System.out.print(startTime+"\t");
						System.out.println(endTime);
						((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
					}
*/
/*
				}

			EventList.get(getActivity()).saveEvents(mEvents);
			 pictureTaken = false;
			 
			}
			ScheduleInfoFragment dialog = new ScheduleInfoFragment("My Schedule",manager1.getText().toString());
			//manager.setText("");
        	dialog.show(fm1, SCHEDULE_INFO_DIALOG);
*/        	

			}
			ScheduleInfoFragment dialog = new ScheduleInfoFragment("My Schedule",manager.getText().toString());
			//manager.setText("");
        	dialog.show(fm, SCHEDULE_INFO_DIALOG);
			return true;


		//case R.id.action_settings:
			//Intent j = new Intent(getActivity(), LoginActivity.class);
			//startActivity(j);
			
			//return true;		
			//return true;
		
		case R.id.about_page:
			AlertDialog aboutDialog = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_DARK)
			.setTitle("About").setMessage("Version1.0")
			.setPositiveButton(android.R.string.ok, null).show();
			WindowManager.LayoutParams params = aboutDialog.getWindow().getAttributes();
			params.alpha = 0.76f;
			aboutDialog.getWindow().setAttributes(params);
			return true;
			
		case R.id.help_page:
			showOverLay();
			return true;
		case R.id.instruction:
			Intent intentI = getActivity().getPackageManager().getLaunchIntentForPackage("com.manishkpr.viewpagerimagegallery");
			intentI.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intentI);
			return true;
		default:
			return super.onOptionsItemSelected(item);	
		} 


	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
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
	private ArrayList<Event> filterEvents(ArrayList<Event> e, Date d) {
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(d);
		
		
		ArrayList<Event> clone = new ArrayList<Event>();
		for(Event event : e) {
			clone.add(event.clone());
		}
		
		
		for(int i = 0; i < clone.size(); i++) {
			
			//Get calendar date of clone Event
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(clone.get(i).getStartDate());
			
			
			
			if((cal2.get(Calendar.MONTH) != cal3.get(Calendar.MONTH)) || (cal2.get(Calendar.DAY_OF_MONTH))
					!= cal3.get(Calendar.DAY_OF_MONTH)) {
			
				clone.remove(clone.get(i));
				i--;
			}
			
			
		}
		
		
		
		return clone;
		
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





	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		Log.d("msg", "back to Calendar view");

		View v = inflater.inflate(R.layout.fragment_wayd, parent, false);

		calendar = (CalendarView)v.findViewById(R.id.calendarView1);

		calendar.setDate(date.getTime());

		//calendar.setBackgroundColor(Color.WHITE);
		//calendar.setFocusedMonthDateColor(Color.BLUE);
		


		///calendar.setShownWeekCount(2);

		//calendar.getDate();

		removeEmptyEvents(mEvents);

		//EventAdapter adapter = new EventAdapter(mEvents);
		lv = (ListView)v.findViewById(R.id.listView1);
		//lv.setAdapter(adapter);

		
		EventAdapter adapter = new EventAdapter(filterEvents(mEvents, date));
		lv.setAdapter(adapter);
		((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
		

		//now display only events from selected day
		//Listener to handle calendar
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			 public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

				Calendar cal = Calendar.getInstance();
				cal.set(year, month, dayOfMonth);
				date = cal.getTime();

								
				lv.setAdapter(new EventAdapter(filterEvents(mEvents, date)));
				((EventAdapter)lv.getAdapter()).notifyDataSetChanged();

				



			 }
		 });



		//Comment
		//Listener Handle the list view	   
		lv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            	Event e = (Event)lv.getItemAtPosition(position);
            	
            	FragmentManager fm = getActivity().getSupportFragmentManager();
            	EventInfoFragment dialog = new EventInfoFragment(e.getTitle(), e.getLocation(), e.getDescription(), e.getTime().toString());
            	dialog.show(fm, EVENT_INFO_DIALOG);
              
            }

       }); 


		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		removeEmptyEvents(mEvents);
		EventAdapter adapter = new EventAdapter(filterEvents(mEvents, date));
		lv.setAdapter(adapter);
		((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
		//Set the calendar day to the event's date recently added
		if(mEvents.size() > 0)
			calendar.setDate(mEvents.get(mEvents.size()-1).getStartDate().getTime());

	}
	
	
	private void populate(Findit f, Date start, String schedule, int weeks)
	{
		Date temp = new Date(start.getTime());

		String event="Mo";
		String startTime=f.findStartTime(schedule);

		String endTime=f.findEndTime(schedule);
		System.out.print(event+"\t");
		
		temp.setHours(Integer.parseInt(startTime.split(":")[0]));
		temp.setMinutes(Integer.parseInt(startTime.split(":")[1]));

		Calendar cal = Calendar.getInstance();
		for(int n = 0; n < weeks; n++)
		{
			Event iii = new Event();

			//cal.set(2014, 5, 27, (int)startTime.charAt(0), 0);
			//cal.set(2014, 5, 27, Integer.parseInt(startTime.split(":")[0]),
					//Integer.parseInt(startTime.split(":")[1]));
			cal.setTime(temp);

			iii.setTime(cal.getTime());
			//iii.setTitle("Class " + (classNumber++));
			iii.setTitle(f.getTitle(schedule));


			mEvents.add(iii);

			System.out.print(startTime+"\t");

			System.out.println(endTime);
			((EventAdapter)lv.getAdapter()).notifyDataSetChanged();
			
			cal.add(cal.DATE, 7);
			temp.setDate(cal.DATE);
		}
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