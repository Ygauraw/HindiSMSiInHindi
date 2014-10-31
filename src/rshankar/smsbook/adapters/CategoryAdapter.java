package rshankar.smsbook.adapters;

import java.util.ArrayList;
import java.util.List;

import rshankar.hindismsinhindi.R;
import rshankar.hindismsinhindi.dataclasses.LeftPannelDataClass;
import rshankar.hindismsinhindi.dataclasses.MainCategory;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CategoryAdapter extends BaseAdapter{
	class LeftPannelData{
		
	}
	private List<LeftPannelDataClass> mLeftPannelDataList=new ArrayList<LeftPannelDataClass>() ;
	

	private int categoryIcons[] = new int[] {
			R.drawable.ic_group,
				R.drawable.ic_engagement,
				R.drawable.ic_marriage,
				R.drawable.ic_anniversary,
				R.drawable.ic_goodmorning,
				R.drawable.ic_goodmorning,
				R.drawable.ic_goodnight,
			R.drawable.ic_group,
				R.drawable.ic_love,
				R.drawable.ic_friendship,
			R.drawable.ic_group,
			R.drawable.ic_group,
				R.drawable.ic_birthday,
		    R.drawable.ic_group,
				R.drawable.ic_diwali,
				R.drawable.ic_diwali,
			 	R.drawable.ic_christmas,
		       	R.drawable.ic_newyear, 
		       	R.drawable.ic_holi,
		       	R.drawable.ic_rakhi,
		     
		    R.drawable.ic_group,
		    R.drawable.ic_group,
		    	R.drawable.ic_joke,
		    R.drawable.ic_group,
		        R.drawable.ic_shayri,
		    R.drawable.ic_group,
		         R.drawable.ic_thought,	
		    R.drawable.ic_group,
		        R.drawable.ic_facebook,R.drawable.ic_whatsapp,
		    R.drawable.ic_group,
		     
		     		
	};
	private List<MainCategory> mCategories=new ArrayList<MainCategory>();
	LayoutInflater mInflater;
	public CategoryAdapter(Context context,List<LeftPannelDataClass> mainCategories){
		mInflater=LayoutInflater.from(context);
		mLeftPannelDataList=mainCategories;

	}
	@Override
	public int getCount() {
		return mLeftPannelDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder;
		
		if(convertView==null||convertView.getTag()==null){
			convertView=mInflater.inflate(R.layout.row_category, null);
			mViewHolder=new ViewHolder();
			mViewHolder.categoryTitle=(TextView)convertView.findViewById(R.id.categoryTitle);
			mViewHolder.icon_category=(ImageView)convertView.findViewById(R.id.icon_category);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		if(mLeftPannelDataList.get(position).tag==0){
			
			convertView.setBackgroundColor(Color.parseColor("#d53300"));
		}else{
			convertView.setBackgroundColor(Color.parseColor("#FF5722"));
		}
		mViewHolder.categoryTitle.setText(mLeftPannelDataList.get(position).categoryName);
		mViewHolder.icon_category.setImageResource(categoryIcons[position]);
		
		return convertView;
	}
	private static class ViewHolder{
		TextView categoryTitle;
		ImageView icon_category;
	}
}