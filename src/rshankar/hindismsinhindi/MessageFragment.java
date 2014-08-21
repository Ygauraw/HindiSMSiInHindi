package rshankar.hindismsinhindi;

import java.util.Locale;

import rshankar.smsbook.adapters.CustomBaseAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MessageFragment extends Fragment{
	String message[];
	 public static final String ARG_PLANET_NUMBER = "planet_number";

     public MessageFragment() {
    	 MainActivity.mDbHelper.open();
    	
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
         int id = getArguments().getInt("catId");
    	 message=MainActivity.mDbHelper.retriveAllMessage(id);
         int i = getArguments().getInt(ARG_PLANET_NUMBER);
       String title=getArguments().getString("title");
         ((ListView) rootView.findViewById(R.id.messageList)).setAdapter(new CustomBaseAdapter(getActivity(),message));
         getActivity().setTitle(title);
         return rootView;
     }
 }

