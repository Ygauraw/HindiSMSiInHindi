package rshankar.hindismsinhindi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

public class MessageAdapter extends ArrayAdapter<String> {
	AdView mAdView;
	ViewHolder mViewHolder;
	LayoutInflater mInflater;
	public static final String SHARED_PREFERENCE_KEY="SMSBOOK_PREFERENCE";
	
	public MessageAdapter(Context context, int resource, String[] objects) {
		super(context, 0, objects);
		mInflater = LayoutInflater.from(context);
     
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String message=getItem(position);
		if(convertView==null||convertView.getTag()==null){
			mViewHolder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.row_message, null);
		    mViewHolder.mMessage=(TextView)convertView.findViewById(R.id.messageText);
		    mViewHolder.messageTextBackground=(LinearLayout)convertView.findViewById(R.id.messageTextBackground);
		    int bg_color=MainActivity.sharedpreferences.getInt("bg_color",Color.RED);
			mViewHolder.messageTextBackground.setBackgroundColor(bg_color);
		    mViewHolder.mMessage.setTextColor(MainActivity.sharedpreferences.getInt("txt_color",Color.WHITE));
		    mViewHolder.mMessage.setTextSize(MainActivity.sharedpreferences.getInt("textsize_list",20));
		    mViewHolder.mMessage.setTypeface(null,MainActivity.sharedpreferences.getInt("textstyle_list",0));
		    convertView.setTag(mViewHolder);
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		mViewHolder.mMessage.setText(message);
		
		return convertView;
	}

	private static class ViewHolder {
		TextView mMessage;
		LinearLayout messageTextBackground;
	}

}
