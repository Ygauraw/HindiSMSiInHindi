package rshankar.smsbook.adapters;

import java.util.Locale;

import rshankar.hindismsinhindi.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class CustomBaseAdapter extends BaseAdapter  implements
TextToSpeech.OnInitListener{
	
	private static final int emojiLove[]=new int[]{R.drawable.ic_new};
    Activity activityContyext;
    String[] messages;
    String[] values;
    LayoutInflater mInflater; 
    Context contextMain;
	  ViewHolder holder;
	  Typeface custom_font;

		private TextToSpeech tts;
		
//	  
//    public CustomBaseAdapter(Context context, String[] messages) {
//        this.contextMain = context;
//        tts = new TextToSpeech(contextMain, this);
//        this.messages = messages;
//        mInflater= (LayoutInflater) this.contextMain.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//    }
    public CustomBaseAdapter(Context context, String[] values) {
        this.contextMain = context;
        activityContyext= (Activity) context;
        tts = new TextToSpeech(activityContyext, this);
        this.values=values;
        mInflater= (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

    }
 
 
    /*private view holder class*/
    private static class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        TextView message;
        TextView facebook;
        TextView email;
        TextView whatsapp;
        TextView share;
        TextView copy;
        TextView speak;
        LinearLayout optionRow;
      
    }
 
    public View getView(final int position, View convertView, ViewGroup parent) {
        
        if (convertView == null||convertView.getTag()==null) {
        		 convertView = mInflater.inflate(R.layout.listrow_sms, null);	
        		 holder = new ViewHolder();
        		 
        	
                 holder.txtTitle = (TextView) convertView.findViewById(R.id.sms);
                 holder.optionRow = (LinearLayout) convertView.findViewById(R.id.optionRow);
                 holder.message = (TextView) convertView.findViewById(R.id.message);
                 holder.email = (TextView) convertView.findViewById(R.id.email);
                 holder.whatsapp = (TextView) convertView.findViewById(R.id.whatsapp);
                 holder.share = (TextView) convertView.findViewById(R.id.share);
                 holder.copy = (TextView) convertView.findViewById(R.id.copy);
                 
                 convertView.setTag(holder);
        	 
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
 
      

//			 holder.txtTitle.setBackgroundColor(Color.parseColor("#4385f6"));
//			 holder.optionRow.setBackgroundColor(Color.parseColor("#FF542E"));
//			 LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//			 lp.setMargins(0, 0, 0, 0);
//			 holder.optionRow.setLayoutParams(lp);
//			 LinearLayout.LayoutParams lpTextView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//			 lpTextView.setMargins(25, 25, 25, 0);
//			 holder.txtTitle.setLayoutParams(lpTextView);
//			 holder.txtTitle.setTextSize(StaticMethodVeriable.TextSize);

		 
		 if(position<10){
		 	SpannableStringBuilder ssb = new SpannableStringBuilder(" "+this.values[position]);
			Bitmap smiley = BitmapFactory.decodeResource(contextMain.getResources(), emojiLove[0]);
			ssb.setSpan( new ImageSpan( smiley ), 0,1, Spannable.SPAN_INCLUSIVE_INCLUSIVE );
			holder.txtTitle.setText( ssb, BufferType.SPANNABLE );
		 }else{
				holder.txtTitle.setText(this.values[position] );
		 }
 

		 
        holder.message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent(Intent.ACTION_VIEW);
				
				sendIntent.putExtra("sms_body", values[position]);

				sendIntent.setType("vnd.android-dir/mms-sms");
				try {
				    contextMain.startActivity(sendIntent);
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(contextMain, "There are no message clients installed.", Toast.LENGTH_SHORT).show();
				}				
				
			}
		});
        holder.email.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto","", null));
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, values[position]);
			contextMain.startActivity(Intent.createChooser(emailIntent, "Send email..."));
			}
		});
//        holder.facebook.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent=new Intent(contextMain,HelloFacebookSampleActivity.class);
//				intent.putExtra("message", values[position]);
//				contextMain.startActivity(intent);
//			}
//		});
        holder.whatsapp.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("unused")
			@Override
			public void onClick(View v) {
				Intent waIntent = new Intent(Intent.ACTION_SEND);
			    waIntent.setType("text/plain");
			            String text = values[position];
			    waIntent.setPackage("com.whatsapp");
			    if (waIntent != null) {
			        waIntent.putExtra(Intent.EXTRA_TEXT, text);//
			        contextMain.startActivity(Intent.createChooser(waIntent, "Share with"));
			    } else {
			        Toast.makeText(contextMain, "WhatsApp not Installed", Toast.LENGTH_LONG)
			                .show();
			    }	
				
			}
		});
        
        holder.copy.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				 ClipboardManager clipboard = (ClipboardManager)contextMain.getSystemService(contextMain.CLIPBOARD_SERVICE); 
				 ClipData clip = ClipData.newPlainText("smsbook",  values[position]);
				 clipboard.setPrimaryClip(clip);
				 
			
			        Toast.makeText(contextMain, "Text copied to clipboard", Toast.LENGTH_LONG)
			                .show();
			   
				
			}
		});
        holder.share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share By SMS BOOK!!");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, values[position]);
				contextMain.startActivity(Intent.createChooser(shareIntent, "Share via"));
				
			}
		});
        
        return convertView;
    }
 
    @Override
    public int getCount() {
        return this.values.length;
    }
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			// tts.setPitch(5); // set pitch level

			// tts.setSpeechRate(2); // set speech speed rate

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			} else {
				
			}

		} else {
			Log.e("TTS", "Initilization Failed");
		}

	}

	private void speakOut(String text ) {

//		String text = txtText.getText().toString();

		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	


}
