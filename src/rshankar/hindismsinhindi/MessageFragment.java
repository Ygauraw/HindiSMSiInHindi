package rshankar.hindismsinhindi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


public class MessageFragment extends Fragment{
	String message[];
	String finalmessage="";
	 public static final String ARG_PLANET_NUMBER = "planet_number";
	 ListView messageList;
	 String messageSeprator="*****(1)*****";
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
       messageList=(ListView) rootView.findViewById(R.id.messageList);
   
       messageList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
       messageList.setMultiChoiceModeListener(new ModeCallback());
       messageList.setLongClickable(true);
//       messageList.setAdapter( new ArrayAdapter<String>(getActivity(),
//               R.layout.listrow_sms, message));
               
    
       
     
//       messageList.setAdapter(new CustomBaseAdapter(getActivity(),message));
//         getActivity().setTitle(title);
         return rootView;
     }
     @Override
    public void onResume() {
    	super.onResume();
    	   messageList.setAdapter( new MessageAdapter(getActivity(), 0, message));
    	   messageSeprator=MainActivity.sharedpreferences.getString("seprator_list","*****(1)*****");
    }
     
     @TargetApi(Build.VERSION_CODES.HONEYCOMB)
 	private class ModeCallback implements ListView.MultiChoiceModeListener {

         @TargetApi(Build.VERSION_CODES.HONEYCOMB)
 		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
             MenuInflater inflater = getActivity().getMenuInflater();
             inflater.inflate(R.menu.list_select_menu, menu);
             mode.setTitle("Select Items");
             return true;
         }

         public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
             return true;
         }

         @SuppressLint("NewApi")
 		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
             switch (item.getItemId()) {
             case R.id.share:
            	 Intent sendIntent = new Intent();
            	 sendIntent.setAction(Intent.ACTION_SEND);
            	 sendIntent.putExtra(Intent.EXTRA_TEXT,finalmessage);
            	 sendIntent.setType("text/plain");
            	 startActivity(sendIntent);
            	 
            	 finalmessage="";
//                 Toast.makeText(getActivity(), "Shared " +messageList.getCheckedItemCount() +
//                         " items", Toast.LENGTH_SHORT).show();
                 mode.finish();
                 break;
             default:
                 Toast.makeText(getActivity(), "Clicked " + item.getTitle(),
                         Toast.LENGTH_SHORT).show();
                 break;
             }
             return true;
         }

         public void onDestroyActionMode(ActionMode mode) {
         }
 int index=0;
 int positionarray[]=new int[20];
         @TargetApi(Build.VERSION_CODES.HONEYCOMB)
 		@SuppressLint("NewApi")
 		public void onItemCheckedStateChanged(ActionMode mode,
                 int position, long id, boolean checked) {
        	 
//         	positionarray[++index]=position;
             final int checkedCount = messageList.getCheckedItemCount();
             switch (checkedCount) {
                 case 0:
                     mode.setSubtitle(null);
                     break;
                 case 1:
                	 index=0;
                	 finalmessage="";
                	 if(checkedCount<message.length){
                	String temp=messageSeprator;
                	 finalmessage+="\n"+ messageSeprator.substring(0,6)+checkedCount+messageSeprator.substring(7)+"\n";
                	 
              		 finalmessage+=message[position];
              		
                     mode.setTitle("One item selected");
                	 }
                    
                     break;
                 default:
                	
                	 if(checkedCount<message.length){
                		 finalmessage+="\n"+ messageSeprator.substring(0,6)+checkedCount+messageSeprator.substring(7)+"\n";
                         finalmessage+=message[position];
              		mode.setTitle("" + checkedCount + " items selected");
                	 }
                	
                 	
//                 	 Toast.makeText(getActivity(), "SIs" + count, Toast.LENGTH_SHORT).show();
                     
                     break;
             }
            
         }
         
     }

     
 }

