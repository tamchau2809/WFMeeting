package com.example.com08.wfmeeting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ListviewCustomAdapter extends BaseAdapter
{
	private String[] title;
	private int[] image;
	private LayoutInflater inflater;
	
	ListviewCustomAdapter(Context fragment, String[] text, int[] img) {
		// TODO Auto-generated constructor stub
		title = text;
		image = img;
		inflater = LayoutInflater.from(fragment);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return title.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.custom_lv_menu, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
		}
		else
			holder = (ViewHolder)convertView.getTag();
		
		holder.tvOptionName = (TextView)convertView.findViewById(R.id.tvOptionName);
		holder.tvOptionName.setText(title[position]);
		
		holder.imgOptionIcon = (ImageView)convertView.findViewById(R.id.imgOptionIcon);
		holder.imgOptionIcon.setImageResource(image[position]);
		
		return convertView;
	}
	
	private class ViewHolder
	{
	    TextView tvOptionName;
	    ImageView imgOptionIcon;
	}
}
