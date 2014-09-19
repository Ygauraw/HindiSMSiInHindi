/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rshankar.hindismsinhindi;

import java.util.ArrayList;
import java.util.List;

import rshankar.hindismsinhindi.dataclasses.Category;
import rshankar.smsbook.adapters.CategoryAdapter;
import rshankar.smsbook.database.MessageDBAdapter;
import rshankar.smsbook.settings.HindiSMSInHindiSettings;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yrkfgo.assxqx4.AdListener;
import com.yrkfgo.assxqx4.MA;

public class MainActivity extends Activity implements AdListener{
	private static final String TAG = "MainActivity";

	private static final int RESULT_SETTINGS = 1;

	public static SharedPreferences sharedpreferences;
	public static final String SHARED_PREFERENCE_KEY="SMSBOOK_PREFERENCE";
	public static MessageDBAdapter mDbHelper;
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    public static List<Category> mCategories=new ArrayList<Category>();
    private static MA air;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(air==null)
        	air=new MA(this, this, false);
       //Caching Smartwall Ad. 
        air.callSmartWallAd();
        air.call360Ad(this, 0, true, null);
        
        	
        sharedpreferences=getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
     	mDbHelper = new MessageDBAdapter(this);
   		mDbHelper.createDatabase();
   		mDbHelper.open();
   		
   		mCategories.clear();
  		mCategories=mDbHelper.retriveAllCategory();
  		mPlanetTitles=new String[mCategories.size()];
  		for(int i=0;i<mCategories.size();i++)
  			{
  			mPlanetTitles[i]=mCategories.get(i).getCategoryName();
  			}
  			

        mTitle = mDrawerTitle = getTitle();
//        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new CategoryAdapter(this,mCategories));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(getString(R.string.drawer_open));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash, menu);
        return super.onCreateOptionsMenu(menu);
    }

   
  

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putInt(MessageFragment.ARG_PLANET_NUMBER, position);
        args.putString("title", mCategories.get(position).getCategoryName());
        args.putInt("catId", mCategories.get(position).getCategoryId());
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
        getActionBar().setSubtitle("Long press to start selection");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

	@Override
	public void noAdAvailableListener() {
//	Toast.makeText(MainActivity.this, "noAdAvailableListener", Toast.LENGTH_SHORT).show();
	
		
	}

	@Override
	public void onAdCached(AdType arg0) {
		try {
		      air.showCachedAd(this, AdType.smartwall);
		    } catch (Exception e) 
		     {
		      super.onBackPressed();
		     }
//		Toast.makeText(MainActivity.this, "onAdCached", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onAdError(String arg0) {
//		Toast.makeText(MainActivity.this, "onAdError", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onSDKIntegrationError(String arg0) {
//		Toast.makeText(MainActivity.this, "onSDKIntegrationError", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onSmartWallAdClosed() {
//		Toast.makeText(MainActivity.this, "onSmartWallAdClosed", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onSmartWallAdShowing() {
//		Toast.makeText(MainActivity.this, "onSmartWallAdShowing", Toast.LENGTH_SHORT).show();
		
	}
	@Override
	public void onBackPressed() {
//		Toast.makeText(MainActivity.this, "onBackPressed", Toast.LENGTH_SHORT).show();
	//Displaying Cached SmartWall Ad
	try {
	      air.showCachedAd(this, AdType.smartwall);
	    } catch (Exception e) 
	     {
	      super.onBackPressed();
	     }
	}


	    /* Called whenever we call invalidateOptionsMenu() */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        return super.onPrepareOptionsMenu(menu);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	  Intent intent;
	         // The action bar home/up action should open or close the drawer.
	         // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	      
	        
	        switch(item.getItemId()) {
	       
	        case R.id.settings:
	             intent = new Intent(MainActivity.this,HindiSMSInHindiSettings.class);
	             startActivityForResult(intent, RESULT_SETTINGS);
	           break;
	          
	        case R.id.rateapp:
	        	intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://details?id=rshankar.hindismsinhindi"));
	        	startActivity(intent);
	            break;
	        case R.id.shareapp:
	        	Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try It Once!!");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "market://details?id=rshankar.hindismsinhindi");
				startActivity(Intent.createChooser(shareIntent, "Share via"));

	            break;
	        case R.id.moreapp:
	        	intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://search?q=pub:RShankar"));
	        	startActivity(intent);
	            break;
	        case R.id.facebookpage:
	          	intent = new Intent(Intent.ACTION_VIEW);
	             intent.setData(Uri.parse("https://www.facebook.com/pages/Hindi-Sms-In-Hindi/355326774521023"));
	            startActivity(intent);
	            break;
	        case R.id.exit:
	        	intent = new Intent(Intent.ACTION_MAIN);
	        	intent.addCategory(Intent.CATEGORY_HOME);
	        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	startActivity(intent);
	        	finish();
	            break;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
			return true;
	        
	    }

}