package name.zwc.mygreatidea.android.common;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AdapterBase extends BaseAdapter
{
	public Object[] datasource = null;
	protected int layoutID;
	protected LayoutInflater inflater;
	public AdapterBase(Activity context, int layoutID)
	{
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layoutID = layoutID;
	}
	public int getCount()
	{
		if (datasource == null)
		{
			return 0;
		}
		return datasource.length;
	}

	public Object getItem(int position)
	{
		return datasource[position];
	}

	public long getItemId(int position)
	{
		return 0;
	}
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = inflater.inflate(layoutID, null);
		}
		
		return convertView;
	}
}
