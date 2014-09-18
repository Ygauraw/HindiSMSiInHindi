package rshankar.smsbook.adapters;

import java.util.ArrayList;
import java.util.List;

import rshankar.hindismsinhindi.R;
import rshankar.hindismsinhindi.dataclasses.Category;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CategoryAdapter extends BaseAdapter{

	private int categoryIcons[] = new int[] { R.drawable.ic_love,
			R.drawable.ic_friendship, R.drawable.ic_birthday,
			R.drawable.ic_joke, R.drawable.ic_thought, R.drawable.ic_diwali,
		R.drawable.ic_diwali,R.drawable.ic_christmas,R.drawable.ic_newyear, 
		R.drawable.ic_holi,R.drawable.ic_rakhi,R.drawable.ic_facebook,R.drawable.ic_whatsapp,R.drawable.ic_shayri		
	};
	private List<Category> mCategories=new ArrayList<Category>();
	LayoutInflater mInflater;
	public CategoryAdapter(Context context,List<Category> categories){
		mInflater=LayoutInflater.from(context);
		mCategories=categories;
	}
	@Override
	public int getCount() {
		return mCategories.size();
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
		mViewHolder.categoryTitle.setText(mCategories.get(position).getCategoryName());
		mViewHolder.icon_category.setImageResource(categoryIcons[position]);
		
		return convertView;
	}
	private static class ViewHolder{
		TextView categoryTitle;
		ImageView icon_category;
	}
}