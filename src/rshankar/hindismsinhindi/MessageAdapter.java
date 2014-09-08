package rshankar.hindismsinhindi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MessageAdapter extends ArrayAdapter<String> {
	AdView mAdView;
	ViewHolder mViewHolder;
	LayoutInflater mInflater;

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
//			mViewHolder.mAdView = (AdView)convertView.findViewById(R.id.adView);
		    mViewHolder.mMessage=(TextView)convertView.findViewById(R.id.messageText);
		    convertView.setTag(mViewHolder);
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
//		mViewHolder.mAdView.loadAd(new AdRequest.Builder().build());
		mViewHolder.mMessage.setText(message);
		
		return convertView;
	}

	private static class ViewHolder {
		TextView mMessage;
//		AdView mAdView;
	}

}
