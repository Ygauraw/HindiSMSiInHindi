package rshankar.smsbook.settings;

import java.util.Set;

import rshankar.hindismsinhindi.MainActivity;
import rshankar.hindismsinhindi.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.h6ah4i.android.compat.preference.MultiSelectListPreferenceCompat;


public class HindiSMSInHindiSettings extends PreferenceActivity implements OnPreferenceChangeListener,     
OnSharedPreferenceChangeListener{
//	 MultiSelectListPreferenceCompat multiselpref;
	 
	 private static final String KEY_LANGUAGE_MESSAGE_OF_THE_DAY_SETTINGS = "language_list";
	    private static final String KEY_CATEGORY_MESSAGE_OF_THE_DAY_SETTINGS = "category_list";
	    private static final String KEY_TEXT_SIZE = "textsize_list";
	    private static final String KEY_TEXT_STYLE = "textstyle_list";
	    private static final String KEY_SEPRATOR="seprator_list"; 

		
	    ListPreference categories,language,textsize,textstyle,seprator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.setting_preference);
        
//        multiselpref = (MultiSelectListPreferenceCompat) findPreference("key_text");
//        multiselpref.setOnPreferenceChangeListener(this);

     
        
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);  
        prefs.registerOnSharedPreferenceChangeListener(this);   
        
//        categories=(ListPreference)findPreference(KEY_CATEGORY_MESSAGE_OF_THE_DAY_SETTINGS);
        textsize=(ListPreference)findPreference(KEY_TEXT_SIZE);
        textstyle=(ListPreference)findPreference(KEY_TEXT_STYLE);
        seprator=(ListPreference)findPreference(KEY_SEPRATOR);
        
        
        
//        if (categories != null) {
//            CharSequence categoriesentries[] = new String[MainActivity.mCategories.size()];
//            CharSequence categoriesentryValues[] = new String[MainActivity.mCategories.size()];
//            int i = 0;
//            for (Category categories : MainActivity.mCategories) {
//            	categoriesentries[i] = categories.getCategoryName();
//            	categoriesentryValues[i] = Integer.toString(categories.getCategoryId());
//                i++;
//            }
//            categories.setEntries(categoriesentries);
//            categories.setEntryValues(categoriesentryValues);
//            categories.setSummary(categories.getEntry());  
//            categories.setDefaultValue("1");
////            multiselpref.setEntries(categoriesentries);
//        }
        if(textsize!=null){
        	textsize.setSummary(textsize.getEntry());  
        }
        if(textstyle!=null){
        	textstyle.setSummary(textstyle.getEntry());  
        }
        if(seprator!=null){
        	seprator.setSummary(seprator.getEntry());  
        }

    }


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if(key.equals("language_list")){
			language.setSummary(language.getEntry());
		}
		 if(key.equals("category_list")){
			categories.setSummary(categories.getEntry());
		}if(key.equals("textsize_list")){
			textsize.setSummary(textsize.getEntry());
			 Editor editor = MainActivity.sharedpreferences.edit();
	         editor.putInt("textsize_list",Integer.parseInt(textsize.getValue().toString()));
	         editor.commit();
		}if(key.equals(KEY_SEPRATOR)){
			 seprator.setSummary(seprator.getEntry());
			 Editor editor = MainActivity.sharedpreferences.edit();
	         editor.putString(KEY_SEPRATOR, seprator.getEntry().toString());
	         editor.commit();
		}
		 if(key.equals("bg_color")){
            Editor editor = MainActivity.sharedpreferences.edit();
        	editor.putInt("bg_color", sharedPreferences.getInt("bg_color", 0xff5722));
        	editor.commit();
       } if(key.equals("txt_color")){
           Editor editor = MainActivity.sharedpreferences.edit();
        	editor.putInt("txt_color",sharedPreferences.getInt("txt_color", Color.WHITE));
        	editor.commit();
       }
       if(key.equals(KEY_TEXT_STYLE)){
    		textstyle.setSummary(textstyle.getEntry());  
           Editor editor = MainActivity.sharedpreferences.edit();
        	editor.putInt(KEY_TEXT_STYLE,Integer.parseInt(textstyle.getValue()));
        	editor.commit();
       }else {
         
       }
		
	}


	

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		 final String key = preference.getKey();
	        if (key.equals("key_text")) {
	            final MultiSelectListPreferenceCompat multiselpref = (MultiSelectListPreferenceCompat) preference;
	            Set<String> selectedValue=(Set<String>) newValue;
//	            multiselpref.setSummary((Set<String>) newValue); 
//	            multiselpref.setSummary(makeSummaryText(mOrigSummaryText, (Set<String>) newValue));

	            return true;
	        }else {
	            return false;
	        }
			
	}
    
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }


	
    

}

