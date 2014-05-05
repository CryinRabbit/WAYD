package com.cryinrabbit.whatareyoudoingplanner;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class WaydActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new WaydFragment();
	}


}
