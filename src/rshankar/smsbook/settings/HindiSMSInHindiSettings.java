package rshankar.smsbook.settings;

import java.util.Set;

import rshankar.hindismsinhindi.MainActivity;
import rshankar.hindismsinhindi.R;
import rshankar.hindismsinhindi.dataclasses.Category;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.h6ah4i.android.compat.preference.MultiSelectListPreferenceCompat;


public class HindiSMSInHindiSettings extends PreferenceActivity implements OnPreferenceChangeListener,     
OnPreferenceClickListener,OnSharedPreferenceChangeListener {
	 MultiSelectListPreferenceCompat multiselpref;
	 private static final String KEY_LANGUAGE_MESSAGE_OF_THE_DAY_SETTINGS = "language_list";
	    private static final String KEY_CATEGORY_MESSAGE_OF_THE_DAY_SETTINGS = "category_list";
	    
//	    private List<Language> mLanguages=new ArrayList<Language>();
//		private List<Category> mCategories=new ArrayList<Category>();
		
	    ListPreference categories,language;
	    
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.setting_preference);
        
        multiselpref = (MultiSelectListPreferenceCompat) findPreference("key_text");
        multiselpref.setOnPreferenceChangeListener(this);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);  
        prefs.registerOnSharedPreferenceChangeListener(this);   
        
        categories=(ListPreference)findPreference(KEY_CATEGORY_MESSAGE_OF_THE_DAY_SETTINGS);
        language=(ListPreference)findPreference(KEY_LANGUAGE_MESSAGE_OF_THE_DAY_SETTINGS);
        
        if (language != null) {
//          
//            CharSequence entries[] = new String[MainActivity.mLanguages.size()];
//            CharSequence entryValues[] = new String[MainActivity.mLanguages.size()];
            int i = 0;
//            for (Language language : MainActivity.mLanguages) {
//                entries[i] = language.getLanguageName();
//                entryValues[i] = Integer.toString(language.getLanguageId());
//                i++;
//            }
//            language.setEntries(entries);
//            language.setEntryValues(entryValues);
//            language.setSummary(language.getEntry());  
//            language.setDefaultValue("1");
        }
        
        if (categories != null) {
            
            CharSequence categoriesentries[] = new String[MainActivity.mCategories.size()];
            CharSequence categoriesentryValues[] = new String[MainActivity.mCategories.size()];
            int i = 0;
            for (Category categories : MainActivity.mCategories) {
            	categoriesentries[i] = categories.getCategoryName();
            	categoriesentryValues[i] = Integer.toString(categories.getCategoryId());
                i++;
            }
            categories.setEntries(categoriesentries);
            categories.setEntryValues(categoriesentryValues);
            categories.setSummary(categories.getEntry());  
            categories.setDefaultValue("1");
            
            multiselpref.setEntries(categoriesentries);
//            multiselpref.setEntryValues(categoriesentryValues);
//            multiselpref.setSummary(categories.getEntry()); 
          //  multiselpref.setDefaultValue("10");
         
            
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
		}
		
		
	}


	@Override
	public boolean onPreferenceClick(Preference arg0) {
		// TODO Auto-generated method stub
		return false;
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
	        } else {
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

