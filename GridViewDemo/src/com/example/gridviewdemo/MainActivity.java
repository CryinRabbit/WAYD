package com.example.gridviewdemo;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
	List<String> list;
	GridView grid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        list=new ArrayList<String>();
        grid=(GridView) findViewById(R.id.gridView1);
        
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wendsday");
        list.add("Thursday");
        list.add("Friday");
       
        ArrayAdapter<String> adp=new ArrayAdapter<String> (this,
        		android.R.layout.simple_dropdown_item_1line,list);
        grid.setAdapter(adp);
        
        grid.setOnItemClickListener(new OnItemClickListener() {
 
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
				
			Toast.makeText(getBaseContext(), list.get(arg2),
				Toast.LENGTH_SHORT).show();
		}
	});
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }    
}