package com.example.gridaddview;


import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
	GridView grid;
	Button add;
	EditText et;
	List<String> list;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        list=new ArrayList<String> ();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        
        grid = (GridView) findViewById(R.id.gridView1);
        et = (EditText) findViewById(R.id.editText1);
        add= (Button) findViewById(R.id.button1);
        add();
        
        add.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				
			list.add(et.getText().toString());
			add();
		}
	});        
    }
 
    public void add()
    {
    	ArrayAdapter<String> adp=new ArrayAdapter<String> (getBaseContext(),
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
