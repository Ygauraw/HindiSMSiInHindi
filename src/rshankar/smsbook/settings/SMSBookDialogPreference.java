package rshankar.smsbook.settings;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;



public class SMSBookDialogPreference extends DialogPreference  {

	public SMSBookDialogPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        persistBoolean(positiveResult);
    }

	
//	EditText pin_et;
//	Button pincancel_but;
//	public DialogPreference(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		// TODO Auto-generated constructor stub
//		setPersistent(false);
//	    setDialogLayoutResource(R.layout.dialog_preference_layout);
//	}
//
//	 @Override
//     protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
//             builder.setTitle("Test Dialog");
//             builder.setPositiveButton(null, null);
//             builder.setNegativeButton(null, null);
//             super.onPrepareDialogBuilder(builder);  
//     }
//	 
//	 @Override
//     public void onBindDialogView(View view){
////             pin_et = (EditText)view.findViewById(R.id.pin_et);
////
//             pincancel_but = (Button)view.findViewById(R.id.pincancel_but);
//             pincancel_but.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					
//					
//				}
//			});
//           
//             super.onBindDialogView(view);
//     }
//	 
//	 @Override
//	    protected void onDialogClosed(boolean positiveResult) {
//	        super.onDialogClosed(positiveResult);
//	        persistBoolean(positiveResult);
//	    }

}
