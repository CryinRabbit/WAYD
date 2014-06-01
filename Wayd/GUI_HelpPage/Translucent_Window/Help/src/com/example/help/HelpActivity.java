package com.example.help;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.WindowManager;
import android.widget.Button;
import android.content.DialogInterface;

public class HelpActivity extends ActionBarActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		Button buttonStartDialog = (Button)findViewById(R.id.start);
		buttonStartDialog.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//Create AlertDialog
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(HelpActivity.this);
				myAlertDialog.setTitle("--- Title ---");

				myAlertDialog.setMessage("Alert Dialog Message");

				myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					// do something when the button is clicked
					public void onClick(DialogInterface arg0, int arg1) {
						//... 
					}});

				myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					// do something when the button is clicked
					public void onClick(DialogInterface arg0, int arg1) {
						//... 
					}});

				AlertDialog dialog = myAlertDialog.show();

				WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
				params.alpha = 0.6f;
				dialog.getWindow().setAttributes(params);
			}});
	}

}

/*
/*
private void showDialog(){
		LayoutInflater layoutInflater = this.getLayoutInflater();
		View customDialog = layoutInflater.inflate(R.layout.dialog_signin, null);
		Dialog dialog = new Dialog(this);
		dialog.setTitle("I am a Dialog");
		dialog.setContentView(customDialog);
		dialog.show();
	}

private void showCommonAlertDialog() {

	AlertDialog.Builder builder = new AlertDialog.Builder(HelpActivity.this);
	AlertDialog dialog = builder.create();
	dialog.setTitle("AlertDialog");
	dialog.setIcon(R.drawable.ic_launcher);
	dialog.setButton(DialogInterface.BUTTON_POSITIVE, "確定",
			new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					Log.e(TAG, "確定按鈕：" + which);
				}
			});
	dialog.show();
}

private void showCommonAlertDialog2() {

	new AlertDialog.Builder(HelpActivity.this)
			.setTitle("AlertDialog")
			.setIcon(R.drawable.ic_launcher)
			.setPositiveButton("確定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					Log.e(TAG, "showCommonAlertDialog clicked");
				}
			}).show();
}

private void showTransDialog() {

	AlertDialog alertDialog = new AlertDialog.Builder(this)
			.setMessage("透明對話框")
			.setPositiveButton("確定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					Log.e(TAG, "確定按鈕：" + which);
				}
			}).create();
	Window window = alertDialog.getWindow();
	WindowManager.LayoutParams lp = window.getAttributes();
	lp.alpha = 0.8f;// 這裏設置透明度
	window.setAttributes(lp);
	alertDialog.show();
}
 */
//***************************************************************	
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
 * A placeholder fragment containing a simple view.
 */

/*
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_help, container, false);
            return rootView;
        }
    }

}
 */